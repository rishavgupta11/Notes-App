package com.example.notesapp.controller;

import com.example.notesapp.dto.CreateNoteRequest;
import com.example.notesapp.dto.UpdateNoteRequest;
import com.example.notesapp.entity.Note;
import com.example.notesapp.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
// @CrossOrigin(origins = "http://localhost:3000")  // This is fine for local testing
public class NoteController {

    @Autowired
    private NoteService noteService;

    // Fixed: Made userId optional for testing
    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes(
            @RequestParam(required = false, defaultValue = "default") String userId) {
        List<Note> notes = noteService.findByUserId(userId);
        return ResponseEntity.ok(notes);
    }

    // Fixed: Added /notes path
    @PostMapping("/notes")
    public ResponseEntity<Note> createNote(@RequestBody CreateNoteRequest request) {
        try {
            Note note = noteService.createNote(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(note);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable String id) {
        return noteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable String id, @RequestBody UpdateNoteRequest request) {
        try {
            Note updated = noteService.updateNote(id, request);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String id) {
        try {
            noteService.deleteNote(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/share/{shareId}")
    public ResponseEntity<Note> getSharedNote(@PathVariable String shareId) {
        return noteService.findByShareId(shareId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Notes API is running");
    }
}
