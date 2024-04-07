package com.example.efabackend.entity;

import jakarta.persistence.*;

import java.util.Arrays;


@Entity
@Table(name = "file")

public class file {
    @Id
    @Column(name = "id", length = 45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nameFile", length = 255)
    private String nameFile;
    @Column(name = "imgFile", length = 255)
    private String imgFile;


    @Lob
    @Column(name = "urlFile", length = 429496729, columnDefinition = "LONGBLOB")
    private byte[] urlFile;

    public file(Long id, String nameFile, String imgFile, byte[] urlFile) {
        this.id = id;
        this.nameFile = nameFile;
        this.imgFile = imgFile;
        this.urlFile = urlFile;
    }


    public file( String nameFile, String imgFile, byte[] urlFile) {
        this.nameFile = nameFile;
        this.imgFile = imgFile;
        this.urlFile = urlFile;
    }

    public file() {
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

    @Override
    public String toString() {
        return "file{" +
                "id=" + id +
                ", nameFile='" + nameFile + '\'' +
                ", imgFile='" + imgFile + '\'' +
                ", urlFile=" + Arrays.toString(urlFile) +
                '}';
    }
}