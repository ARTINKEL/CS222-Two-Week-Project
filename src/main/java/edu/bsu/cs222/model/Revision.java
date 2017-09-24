package edu.bsu.cs222.model;

import com.google.gson.JsonObject;

public class Revision {

    private String username;
    private String timestamp;

    public Revision(String username, String timestamp) {
        this.username = username;
        this.timestamp = timestamp;
    }
}