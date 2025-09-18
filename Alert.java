package com.example.lakepulse;

public class Alert {
    private String message;
    private String timestamp;

    public Alert(String message, String timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
