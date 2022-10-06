package com.example.uploaddownload.controller;

import com.example.uploaddownload.entity.Attachment;
import com.example.uploaddownload.model.ResponseData;
import com.example.uploaddownload.service.AttachmentServiceImpl;
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

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AttachmentController {

    @Autowired
    private AttachmentServiceImpl attachmentService;





    @PostMapping("/upload")
    public ResponseData upload(@RequestParam("file") MultipartFile file) throws Exception{
        Attachment attachment = attachmentService.saveAttachment(file);
        String downloadURL = getDownloadURL(attachment);

        return new ResponseData(attachment.getName(),downloadURL,file.getContentType(),file.getSize());

    }



    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {

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
                    return new ResponseData(attachment.getName(),downloadUri,attachment.getType(),attachment.getData().length);}).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(attachments);
    }


    private static String getDownloadURL(Attachment attachment) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId())
                .toUriString();
    }

}
