package com.HMCTeam.KeepNotes.repositories;

import com.HMCTeam.KeepNotes.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
