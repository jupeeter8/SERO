package com.example.serobeta0;

import java.util.Date;

public class cmntModal {

    private String Message, User;
    private Date timestamp;

    public cmntModal() {}

    public cmntModal(String message, String user, Date timestamp) {
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
