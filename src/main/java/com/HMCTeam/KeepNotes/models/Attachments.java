package com.HMCTeam.KeepNotes.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "attachment")
@NoArgsConstructor
@Data
public class Attachments {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "attachment_id")
    private String id;

    @Column(name = "attachment_name")
    private String name;
    private String type;

    @ManyToOne
    @JoinColumn(name = "note_id")
    private Note note;
    @Lob
    private byte[] data;


    public Attachments(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }
}