package com.HMCTeam.KeepNotes.controllers;


import com.HMCTeam.KeepNotes.models.Note;
import com.HMCTeam.KeepNotes.models.User;
import com.HMCTeam.KeepNotes.repositories.UserRepository;
import com.HMCTeam.KeepNotes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private UserRepository userRepository;

    //     save or create
    @PostMapping("/{userId}")
    public Note saveNote(@Valid @RequestBody Note note ,@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User Not found"));
        note.setUser(user);
        //validate if note exist
        return noteService.saveNote(note);
    }

    //read or get
    @GetMapping("/{id}")
    public Note notes(@PathVariable Long id) throws Exception {
        return noteService.getNoteById(id);
    }

    @GetMapping("")
    public List<Note> getAllNotes() {
        return noteService.getNotes();
    }


    //Update
    @PutMapping("/{id}")
    public Note updateNote(@RequestBody Note note, @PathVariable("id") Long id) throws Exception {

        return noteService.updateNote(note, id);
    }

    //Delete
    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable("id") Long id) {
        noteService.deleteNote(id);
        return "Deleted Successfully";
    }


}
