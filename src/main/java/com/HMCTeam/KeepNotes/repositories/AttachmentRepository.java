package com.HMCTeam.KeepNotes.repositories;

import com.HMCTeam.KeepNotes.models.Attachment;
import com.HMCTeam.KeepNotes.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    List<Attachment> findByNote(Note note);
}
