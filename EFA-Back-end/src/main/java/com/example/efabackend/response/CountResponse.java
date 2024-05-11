package com.example.efabackend.response;

public class CountResponse {
    private long count;
    private String role;

    public CountResponse(long count, String role) {
        this.count = count;
        this.role = role;
    }

    public long getCount() {
        return count;
    }

    public String getRole() {
        return role;
    }
}
