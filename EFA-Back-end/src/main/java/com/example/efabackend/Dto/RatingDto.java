package com.example.efabackend.Dto;

public class RatingDto {
    private String description;
    private String email;
    private long fileId;
    private int stars ;

    public RatingDto(String description, String email, long fileId, int stars) {
        this.description = description;
        this.email = email;
        this.fileId = fileId;
        this.stars = stars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
