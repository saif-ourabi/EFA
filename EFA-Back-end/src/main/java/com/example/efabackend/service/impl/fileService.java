package com.example.efabackend.service.impl;

import com.example.efabackend.entity.File;

import java.util.List;

public interface fileService {
    List<File> getAllFiles();
    File getFileById(Long id);
    List<File> findFilesByNameFile(String nameFile);
    File updateFile(Long id, String nameFile, String imgFile, byte[] urlFile);
    void deleteFile(Long id);
    File addFile(File file);
}
