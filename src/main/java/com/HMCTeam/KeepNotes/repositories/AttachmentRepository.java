package com.HMCTeam.KeepNotes.repositories;

import com.HMCTeam.KeepNotes.models.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
