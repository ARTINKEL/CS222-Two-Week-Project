package edu.bsu.cs222.model;

import java.time.Instant;
import java.time.ZonedDateTime;

public class Revision {

    private String username;
    private Instant timestamp;

    public Revision(String username, Instant timestamp) {
        this.username = username;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}