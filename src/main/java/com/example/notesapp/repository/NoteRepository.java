package com.example.notesapp.repository;

import com.example.notesapp.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, String> {
    List<Note> findByUserIdOrderByUpdatedAtDesc(String userId);
    Optional<Note> findByShareId(String shareId);
}