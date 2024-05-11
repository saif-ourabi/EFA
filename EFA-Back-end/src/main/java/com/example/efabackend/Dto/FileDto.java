package com.example.efabackend.Dto;

public class FileDto {
    private Long id;
    private String nameFile;
    private String imgFile;
    private byte[] urlFile;

    public FileDto(Long id, String nameFile, String imgFile, byte[] urlFile) {
        this.id = id;
        this.nameFile = nameFile;
        this.imgFile = imgFile;
        this.urlFile = urlFile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getImgFile() {
        return imgFile;
    }

    public void setImgFile(String imgFile) {
        this.imgFile = imgFile;
    }

    public byte[] getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(byte[] urlFile) {
        this.urlFile = urlFile;
    }
}