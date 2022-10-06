package com.HMC.Note.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor

public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "note_id")
    private Long id;

    private String title ;

    private String content;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "user_id")
    private Long userId;


    public Note(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.creationDate = LocalDate.now();
        this.userId = userId;
    }

}
