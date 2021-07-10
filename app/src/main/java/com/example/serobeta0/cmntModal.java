package com.example.serobeta0;

import java.util.Date;

public class cmntModal {

    private String Message, User;
    private String timestamp;

    public cmntModal() {}

    public cmntModal(String message, String user, String timestamp) {
        Message = message;
        User = user;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
