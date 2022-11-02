package com.HMCTeam.KeepNotes.services;

import com.HMCTeam.KeepNotes.models.Attachment;
import com.HMCTeam.KeepNotes.models.Note;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Stream;

public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file, Long noteId) throws Exception;

    Attachment getAttachment(Long fileId) throws Exception;

    Stream<Attachment> getAllFiles();

   List<Attachment> getAttachmentByNote(Long noteId) throws Exception;
}
