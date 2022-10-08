package com.HMCTeam.KeepNotes.repositories;

import com.HMCTeam.KeepNotes.models.Attachments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachments, Long> {
}
