package com.example.uploaddownload.service;

import com.example.uploaddownload.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;
import java.util.stream.Stream;

public interface AttachmentSrevice {
    Attachment saveAttachment(MultipartFile file) throws Exception;

    Attachment getAttachment(String fileId) throws Exception;

    Stream<Attachment> getAllFiles();
}
