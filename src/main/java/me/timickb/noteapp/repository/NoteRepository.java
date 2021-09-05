package me.timickb.noteapp.repository;

import me.timickb.noteapp.model.Category;
import me.timickb.noteapp.model.Note;
import me.timickb.noteapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByUser(User user);
    List<Note> findAllByCategory(Category category);
    List<Note> findAllByTitle(String title);
}
