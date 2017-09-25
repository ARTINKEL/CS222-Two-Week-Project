package edu.bsu.cs222.model;

import java.time.ZonedDateTime;

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

    public ZonedDateTime convertTimezone() {
        return null;
    }
}