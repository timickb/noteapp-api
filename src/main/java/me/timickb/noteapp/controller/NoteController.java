package me.timickb.noteapp.controller;


import me.timickb.noteapp.exception.EntityNotFoundException;
import me.timickb.noteapp.model.Note;
import me.timickb.noteapp.model.request.NoteCreateRequest;
import me.timickb.noteapp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(noteService.getAll());
        } catch(Exception e) {
            return ResponseEntity.ok("An error occurred");
        }
    }

    @GetMapping("/{noteId}")
    public ResponseEntity getOne(@PathVariable Long noteId) {
        try {
            return ResponseEntity.ok(noteService.getOne(noteId));
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody NoteCreateRequest request) {
        try {
            return ResponseEntity.ok(noteService.create(request));
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
