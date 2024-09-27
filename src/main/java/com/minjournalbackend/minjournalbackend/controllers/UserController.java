package com.minjournalbackend.minjournalbackend.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.minjournalbackend.minjournalbackend.models.*;
import com.minjournalbackend.minjournalbackend.services.UserService;

@CrossOrigin("*")
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Hämtar en användare.
    @GetMapping("/user/{username}/{password}")
    public User getUser(@PathVariable String username, @PathVariable String password) {
        return userService.getUser(username, password);
    }

    // Skapar en användare.
    @PostMapping("/user")
    public User setUser(@RequestBody User user) {
        return userService.setUser(user);
    }

    // Skapar en post.
    @PostMapping("/user/{username}/post")
    public Post setPost(@PathVariable String username, @RequestBody Post post) {
        return userService.setPost(username, post);
    }

    // Hämtar alla poster för en viss tidsperiod.
    @GetMapping("/user/{username}/posts")
    public List<Post> getPosts(@PathVariable String username, String minDate, String maxDate) {
        return userService.getPosts(username, minDate, maxDate);
    }
}
