package com.minjournalbackend.minjournalbackend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    // Hämtar alla användare.
    @GetMapping("/users")
    public String getUsers() {
        return "hej";
    }
}
