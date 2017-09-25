package edu.bsu.cs222.model;

import java.time.Instant;

public class Revision {

    private String username;
    private String timestamp;

    public Revision(String username, String timestamp) {
        this.username = username;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public String getTimestamp() {
        return timestamp;
    }
}