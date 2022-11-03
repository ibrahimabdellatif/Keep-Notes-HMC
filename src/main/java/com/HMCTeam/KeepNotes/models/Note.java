package com.HMCTeam.KeepNotes.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "note")
@Data
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long noteId;
    private String title;
    private String content;
    @Column(name = "creation_date")
    private LocalDate creationDate;

    //    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "note")
    private List<Attachment> attachments;



    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @PrePersist
    public void setDate(){
        this.creationDate = LocalDate.now();
    }


}
