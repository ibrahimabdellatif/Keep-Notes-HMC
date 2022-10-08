package com.HMCTeam.KeepNotes;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "1234567";
        String encode = encoder.encode(password);
        System.out.println(encode);
    }
}
