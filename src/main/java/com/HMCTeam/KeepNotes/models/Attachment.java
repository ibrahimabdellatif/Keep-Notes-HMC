package com.HMCTeam.KeepNotes.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "attachment")
@NoArgsConstructor
@Data
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachment_id")
    private Long id;

    @Column(name = "attachment_name")
    private String name;
    private String type;

    @ManyToOne
    @JoinColumn(name = "note_id")
    private Note note;
    @Lob
    private byte[] data;


    public Attachment(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}