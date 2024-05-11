package com.example.efabackend.service.impl;

import com.example.efabackend.entity.file;

import java.util.List;

public interface fileService {
    List<file> getAllFiles();
    file getFileById(Long id);
    List<file> findFilesByNameFile(String nameFile);
    file updateFile(file updatedFile);
    void deleteFile(Long id);

    file addFile(file file);
}
