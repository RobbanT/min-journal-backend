package com.minjournalbackend.minjournalbackend.models;

import java.time.*;

public class Post {
    public enum States { SAD, HAPPY, STRESSED, ANGRY, TIRED }
    private String createdTime, note;
    private States state;

    public Post(String note, States state) {
        createdTime = LocalDateTime.now(ZoneId.of("Europe/Paris")).toString();
        this.note = note;
        this.state = state;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }
}
