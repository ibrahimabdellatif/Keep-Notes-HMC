package com.HMCTeam.KeepNotes.services;

import com.HMCTeam.KeepNotes.models.Note;
import com.HMCTeam.KeepNotes.models.User;
import com.HMCTeam.KeepNotes.repositories.NoteRepository;
import com.HMCTeam.KeepNotes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteById(Long id) throws Exception {
        return noteRepository.findById(id).orElseThrow(() -> new Exception("Note not found " + id));
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(Long.valueOf(id));
    }

    public Note updateNote(Note note, Long noteId) throws Exception {
        Note newNote = getNoteById(noteId);
        if (Objects.nonNull(note.getTitle()) && !"".equalsIgnoreCase(note.getTitle())) {
            newNote.setTitle(note.getTitle());
            newNote.setContent(note.getContent());
        }
        return noteRepository.save(newNote);
    }


    public List<Note> getNotesByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return noteRepository.findByUser(user);
    }


}
