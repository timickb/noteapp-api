package me.timickb.noteapp.service;

import me.timickb.noteapp.exception.EntityNotFoundException;
import me.timickb.noteapp.mapper.NoteMapper;
import me.timickb.noteapp.model.Category;
import me.timickb.noteapp.model.Note;
import me.timickb.noteapp.model.User;
import me.timickb.noteapp.model.request.NoteCreateRequest;
import me.timickb.noteapp.model.response.NoteResponse;
import me.timickb.noteapp.repository.CategoryRepository;
import me.timickb.noteapp.repository.NoteRepository;
import me.timickb.noteapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {
    private NoteRepository noteRepo;
    private UserRepository userRepo;
    private CategoryRepository categoryRepo;
    private NoteMapper noteMapper;

    @Autowired
    public NoteService(NoteRepository noteRepo, UserRepository userRepo, CategoryRepository categoryRepo, NoteMapper noteMapper) {
        this.noteRepo = noteRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.noteMapper = noteMapper;
    }

    public List<Note> getAll() {
        return noteRepo.findAll();
    }

    public Note getOne(Long id) throws EntityNotFoundException {
        return noteRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Note with such id doesn't exist"));
    }

    public List<Note> getAllByUserId(Long userId) throws EntityNotFoundException {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User with such id doesn't exist"));
        return noteRepo.findAllByUser(user);
    }

    public List<Note> getAllByCategoryId(Long categoryId) throws EntityNotFoundException {
        Category category = categoryRepo.findById(categoryId).orElseThrow(
                () -> new EntityNotFoundException("Category with such id doesn't exist"));
        return noteRepo.findAllByCategory(category);
    }


    public List<NoteResponse> getAllByCategoryIdAsResponse(Long categoryId) {
        List<Note> notes = null;
        try {
            notes = getAllByCategoryId(categoryId);
        } catch(EntityNotFoundException e) {
            return new ArrayList<>();
        }
        return notes.stream()
                .map(n -> noteMapper.noteToResponse(n))
                .collect(Collectors.toList());
    }

    public Note create(NoteCreateRequest request) throws EntityNotFoundException {
        Category category = categoryRepo.findById(request.getCategoryId()).orElseThrow(
                () -> new EntityNotFoundException("Category with such id doesn't exist"));
        Note note = Note.createFromRequest(request, category);
        noteRepo.saveAndFlush(note);
        return note;
    }
}
