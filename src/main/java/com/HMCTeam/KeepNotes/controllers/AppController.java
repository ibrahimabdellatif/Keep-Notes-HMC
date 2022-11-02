package com.HMCTeam.KeepNotes.controllers;

import com.HMCTeam.KeepNotes.models.Note;
import com.HMCTeam.KeepNotes.models.User;
import com.HMCTeam.KeepNotes.repositories.UserRepository;
import com.HMCTeam.KeepNotes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(value = "")
public class AppController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NoteService noteService;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return "register_success";
    }

    @GetMapping("/list_users")
    public String viewUsersList(Model model) {
        List<User> usersList = userRepository.findAll();
        model.addAttribute("usersList", usersList);


        return "users";
    }

    @GetMapping("/list_notes")
    public String viewUserNotes(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();
        List<Note> userNotes = noteService.getNotesByEmail(userEmail);
        int noteCount = noteService.getCount(userEmail);
        model.addAttribute("userNotes", userNotes);
        model.addAttribute("noteCount",noteCount);

        return "notes";
    }


}
