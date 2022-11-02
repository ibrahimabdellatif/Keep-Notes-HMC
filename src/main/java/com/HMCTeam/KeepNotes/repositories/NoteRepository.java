package com.HMCTeam.KeepNotes.repositories;
import com.HMCTeam.KeepNotes.models.Note;
import com.HMCTeam.KeepNotes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUser(User user);

    int countAllByUser(User user);


}
