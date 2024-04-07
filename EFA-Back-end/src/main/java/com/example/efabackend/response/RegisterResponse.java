package com.example.efabackend.response;

public class RegisterResponse {
    String message;
    boolean status;

    public RegisterResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public RegisterResponse() {
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
