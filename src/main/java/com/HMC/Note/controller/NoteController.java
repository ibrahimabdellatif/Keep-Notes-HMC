package com.HMC.Note.controller;

import com.HMC.Note.entity.Note;
import com.HMC.Note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NoteController {

    @Autowired
    private NoteService noteService;

    // save or create
    @PostMapping("/notes")
    public Note saveNote(@Valid @RequestBody Note note){
        return noteService.saveNote(note);
    }

    //read or get
    @GetMapping("notes")
    public List<Note> notes(){
        return noteService.getNotes();
    }

    //Update
    @PutMapping("notes/{id}")
    public Note updateNote(@RequestBody Note note ,@PathVariable("id") Long id) throws Exception {

        return noteService.updateNote(note,id);
    }

    //Delete
    @DeleteMapping("notes/{id}")
    public String deleteNote(@PathVariable("id") Long id){
        noteService.deleteNote(id);
        return "Deleted Successfully";
    }

}
