package com.HMCTeam.KeepNotes.controllers;

import com.HMCTeam.KeepNotes.models.Note;
import com.HMCTeam.KeepNotes.repositories.NoteRepository;
import com.HMCTeam.KeepNotes.repositories.UserRepository;
import com.HMCTeam.KeepNotes.services.NoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NoteService noteService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private NoteRepository noteRepository;

    @Test
    public void setUp() throws Exception {
        mockMvc.perform(get("/").with(user("h@gmail.com").password("123456")));
    }
    @Test
    void saveNote() throws Exception {
        Note note = new Note("test","test content");
        doReturn(note).when(noteRepository).save(any());

        Note newNote = noteService.saveNote(note);

        Assertions.assertNotNull(newNote,"Saved done");
        Assertions.assertEquals("test",newNote.getTitle(),"same object");

    }

    @Test
    void notes() {
    }

    @Test
    void getAllNotes() {
    }

    @Test
    void updateNote() {
    }

    @Test
    void deleteNote() {
    }
}