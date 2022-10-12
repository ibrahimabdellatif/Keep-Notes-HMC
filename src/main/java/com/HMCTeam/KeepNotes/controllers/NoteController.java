package com.HMCTeam.KeepNotes.controllers;


import com.HMCTeam.KeepNotes.models.Note;
import com.HMCTeam.KeepNotes.models.User;
import com.HMCTeam.KeepNotes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("")
public class NoteController {

    @Autowired
    private NoteService noteService;

    //     save or create
    @PostMapping("/notes")
    public Note saveNote(@Valid @RequestBody Note note) {
        note.setCreationDate(LocalDate.now());
        return noteService.saveNote(note);
    }

    //read or get
    @GetMapping("notes/{id}")
    public Note notes(@PathVariable Long id) throws Exception {
        return noteService.getNoteById(id);
    }

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteService.getNotes();
    }


    //Update
    @PutMapping("notes/{id}")
    public Note updateNote(@RequestBody Note note, @PathVariable("id") Long id) throws Exception {

        return noteService.updateNote(note, id);
    }

    //Delete
    @DeleteMapping("notes/{id}")
    public String deleteNote(@PathVariable("id") Long id) {
        noteService.deleteNote(id);
        return "Deleted Successfully";
    }


}
