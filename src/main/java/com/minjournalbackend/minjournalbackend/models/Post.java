package com.minjournalbackend.minjournalbackend.models;

import java.time.*;

public class Post {
    public enum States { SAD, HAPPY, STRESSED, ANGRY, TIRED }
    private States state;
    private String createdTime, note;

    public Post(States state, String note) {
        this.state = state;
        this.createdTime = LocalDateTime.now(ZoneId.of("Europe/Paris")).toString();
        this.note = note;
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
