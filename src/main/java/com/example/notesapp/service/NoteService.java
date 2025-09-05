package com.example.notesapp.service;

import com.example.notesapp.entity.Note;
import com.example.notesapp.repository.NoteRepository;
import com.example.notesapp.dto.CreateNoteRequest;
import com.example.notesapp.dto.UpdateNoteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> findByUserId(String userId) {
        return noteRepository.findByUserIdOrderByUpdatedAtDesc(userId);
    }

    public Optional<Note> findById(String id) {
        return noteRepository.findById(id);
    }

    public Optional<Note> findByShareId(String shareId) {
        return noteRepository.findByShareId(shareId);
    }

    public Note createNote(CreateNoteRequest request) {
        Note note = new Note();
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        note.setUserId(request.getUserId());
        return noteRepository.save(note);
    }

    public Note updateNote(String id, UpdateNoteRequest request) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (request.getTitle() != null) {
            note.setTitle(request.getTitle());
        }
        if (request.getContent() != null) {
            note.setContent(request.getContent());
        }

        return noteRepository.save(note);
    }

    public void deleteNote(String id) {
        if (!noteRepository.existsById(id)) {
            throw new RuntimeException("Note not found");
        }
        noteRepository.deleteById(id);
    }
}