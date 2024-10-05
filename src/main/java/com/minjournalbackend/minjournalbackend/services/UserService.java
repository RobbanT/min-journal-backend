package com.minjournalbackend.minjournalbackend.services;

import java.time.LocalDateTime;
import java.util.*;
import com.minjournalbackend.minjournalbackend.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder bcryptEncoder;
    private final MongoOperations mongoOperations;

    public UserService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    private User findUser(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return mongoOperations.findOne(query, User.class);
    }

    // Returnerar angiven användare. Finns inte en användare med angivet
    // användarnamn och lösenord returnerar vi null. Används när en användare vill logga in.
    public User getUser(String username, String password) {
        return findUser(username) != null &&
            bcryptEncoder.matches(password, findUser(username).getPassword()) ? 
            findUser(username) : null;
    }

    // Finns det redan en användare med ett visst användarnamn? Då returnerar vi
    // null. Ingen ny användare skapas. Används när en ny användare försöker registrera sig.
    public User setUser(User user) {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return findUser(user.getUsername()) != null ? null : mongoOperations.insert(user);
    }

    // Skapar nytt inlägg.
    public Post setPost(String username, Post post) {
        User user = findUser(username);
        user.getPosts().add(post);
        Query query = new Query();
        query.addCriteria((Criteria.where("username").is(user.getUsername())));
        mongoOperations.updateFirst(query, Update.update("posts", user.getPosts()), User.class);
        return post;
    }

    // Returnerar alla inlägg för en användare inom en viss tidsperiod.
    public List<Post> getPosts(String username, String minDate, String maxDate) {
        List<Post> posts = findUser(username).getPosts();
        System.out.println(posts.get(0).getCreatedTime());
        posts.removeIf(p -> (
            LocalDateTime.parse(p.getCreatedTime()).isBefore(LocalDateTime.parse(minDate.replace("\"", "") + "T00:00:00")) || 
            LocalDateTime.parse(p.getCreatedTime()).isAfter(LocalDateTime.parse(maxDate.replace("\"", "") + "T00:00:00"))));
        return posts;
    }
}
