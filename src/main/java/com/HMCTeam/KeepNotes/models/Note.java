package com.HMCTeam.KeepNotes.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long NoteId;
    private String title;
    private String content;
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "note")
    private List<Attachments> list;

    public List<Attachments> getList() {
        return list;
    }

    public void setList(List<Attachments> list) {
        this.list = list;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getNoteId() {
        return NoteId;
    }

    public void setNoteId(Long noteId) {
        NoteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }


}
