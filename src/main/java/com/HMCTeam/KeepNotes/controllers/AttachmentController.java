package com.HMCTeam.KeepNotes.controllers;
import com.HMCTeam.KeepNotes.model.ResponseData;
import com.HMCTeam.KeepNotes.models.Attachment;
import com.HMCTeam.KeepNotes.models.Note;
import com.HMCTeam.KeepNotes.services.AttachmentService;
import com.HMCTeam.KeepNotes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;


    @PostMapping("/notes/{noteId}/upload")
    public ResponseData upload(@RequestParam("file") MultipartFile file ,@PathVariable Long noteId) throws Exception{
        Attachment attachment = attachmentService.saveAttachment(file,noteId);
        String downloadURL = getDownloadURL(attachment);

        return new ResponseData(attachment.getName(),downloadURL,file.getContentType(),file.getSize(),noteId);

    }



    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) throws Exception {

        Attachment attachment = attachmentService.getAttachment( fileId);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachment.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ attachment.getName()+"\"")
                .body(new ByteArrayResource(attachment.getData()));
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseData>> getListFiles(){

        List<ResponseData> attachments = attachmentService.getAllFiles().map(
                attachment ->{
                    String downloadUri =getDownloadURL(attachment);
                    return new ResponseData(attachment.getName(),downloadUri,attachment.getType(),attachment.getData().length,attachment.getNote().getNoteId());}).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(attachments);
    }

    @GetMapping("/files/{noteId}")
    public ResponseEntity<List<ResponseData>> getAttachmentForNote(@PathVariable Long noteId) throws Exception {

        List<ResponseData> attachments =  attachmentService.getAttachmentByNote(noteId).stream().map(
                attachment ->{
                    String downloadUri =getDownloadURL(attachment);
                    return new ResponseData(attachment.getName(),downloadUri,attachment.getType(),attachment.getData().length,attachment.getNote().getNoteId());}).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(attachments);

    }

    private static String getDownloadURL(Attachment attachment) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(String.valueOf(attachment.getId()))
                .toUriString();
    }

}
