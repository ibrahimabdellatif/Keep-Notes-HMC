package com.HMC.Note.service;

import com.HMC.Note.entity.Note;
import com.HMC.Note.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;


//    public Note create(String title,LocalDate creationDate){
//        Note note = new Note();
//        note.setTitle(title);
//        note.setCreationDate(creationDate);
//        noteRepository.save(note);
//        return note;
//    }

    // save note
    public Note saveNote(Note note){
        return noteRepository.save(note);
    }

    //read notes
    public List<Note> getNotes(){
        return (List<Note>) noteRepository.findAll();
    }

    //update note
    public Note updateNote(Note note, Long noteId) throws Exception {
        Note newNote = getNote(noteId);
        if(Objects.nonNull(note.getTitle()) && ! "".equalsIgnoreCase(note.getTitle())){
            newNote.setTitle(note.getTitle());
            newNote.setContent(note.getContent());
        }
        return noteRepository.save(newNote);
    }

    //Delete

    public void deleteNote(Long id){
        noteRepository.deleteById(Long.valueOf(id));
    }

    public Note getNote(Long id) throws Exception {
        return noteRepository.findById(id).orElseThrow(()->new Exception("Note not found "+id));
    }
}
