package com.HMCTeam.KeepNotes.services;

import com.HMCTeam.KeepNotes.models.Attachment;
import com.HMCTeam.KeepNotes.repositories.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Stream;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {

        String fileName  = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(fileName.contains("..")){
                throw new Exception("File name invalid");
            }
            Attachment attachment = new Attachment(fileName,file.getContentType(),file.getBytes());

            return attachmentRepository.save(attachment);
        } catch (Exception e) {
            throw new Exception("Could not save file "+ file.getName());
        }

    }

    @Override
    public Attachment getAttachment(Long fileId) throws Exception {
        return attachmentRepository.findById(fileId).orElseThrow(()->new Exception("file not found "+fileId));
    }

    @Override
    public Stream<Attachment> getAllFiles() {
         return attachmentRepository.findAll().stream();
    }
}
