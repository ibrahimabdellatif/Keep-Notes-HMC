package com.HMCTeam.KeepNotes.services;

import com.HMCTeam.KeepNotes.models.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Stream;

public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file) throws Exception;

    Attachment getAttachment(Long fileId) throws Exception;

    Stream<Attachment> getAllFiles();
}
