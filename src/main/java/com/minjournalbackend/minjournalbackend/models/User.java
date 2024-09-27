package com.minjournalbackend.minjournalbackend.models;

import java.util.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {
    private String username, password;
    private List<Post> posts;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.posts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
