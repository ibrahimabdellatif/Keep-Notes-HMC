package com.HMCTeam.KeepNotes.services;

import com.HMCTeam.KeepNotes.models.Attachment;
import com.HMCTeam.KeepNotes.models.Note;
import com.HMCTeam.KeepNotes.repositories.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Stream;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private NoteService noteService;

    @Override
    public Attachment saveAttachment(MultipartFile file ,Long noteId) throws Exception {

        String fileName  = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(fileName.contains("..")){
                throw new Exception("File name invalid");
            }
            Attachment attachment = new Attachment(fileName,file.getContentType(),file.getBytes());
            Note note = noteService.getNoteById(noteId);
            attachment.setNote(note);
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

    @Override
    public List<Attachment> getAttachmentByNote(Long noteId) throws Exception {
        Note note = noteService.getNoteById(noteId);
        return attachmentRepository.findByNote(note);
    }
}
