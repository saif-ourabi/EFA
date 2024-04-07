package com.example.efabackend.Dto;

public class FileDto {
    private String nameFile;
    private String imgFile;
    private byte[] urlFile;

    public FileDto(String nameFile, String imgFile, byte[] urlFile) {
        this.nameFile = nameFile;
        this.imgFile = imgFile;
        this.urlFile = urlFile;
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
