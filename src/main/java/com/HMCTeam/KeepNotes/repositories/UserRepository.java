package com.HMCTeam.KeepNotes.repositories;

import com.HMCTeam.KeepNotes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User , Long> {

    //@Query("SELECT u FROM User u WHERE u.email = ?1")
    //@Query("FROM User u WHERE u.email = ?1")
    User findUserByEmail(String email);
}
