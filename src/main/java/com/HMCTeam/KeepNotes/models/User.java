package com.HMCTeam.KeepNotes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, length = 64) // 64 will be encrypted password
    private String password;


    //    @JoinTable(
//            name = "Notes",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "note_id")
//    )
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Note> list;


    public User(User user) {

    }


}
