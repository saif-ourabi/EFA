package com.example.efabackend.response;

public class LoginResponse {
     String message;
     boolean status;
     String token;
    public LoginResponse(String message, boolean status, String token) {
        this.message = message;
        this.status = status;
        this.token=token;
    }

    public LoginResponse() {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

