package com.example.efabackend.service;

import com.example.efabackend.Repo.FileRepository;
import com.example.efabackend.entity.file;
import com.example.efabackend.exception.FileNotFoundException;
import com.example.efabackend.service.impl.fileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FileServiceImpl implements fileService {
    @Autowired
    private FileRepository fileRepository;

    @Override
    public List<file> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    public file getFileById(Long id) {
        return fileRepository.findById(id).orElse(null);
    }

    @Override
    public List<file> findFilesByNameFile(String nameFile) {
        return fileRepository.findBynameFileContainingIgnoreCase(nameFile);
    }

    @Override
    public file updateFile(file updatedFile) {
        Optional<file> optionalFile = fileRepository.findById(updatedFile.getId());
        if (optionalFile.isEmpty()) {
            throw new FileNotFoundException("File with this id " + updatedFile.getId() + " not found");
        } else {
            file existingFile = optionalFile.get();
            existingFile.setNameFile(updatedFile.getNameFile());
            existingFile.setImgFile(updatedFile.getImgFile());
            existingFile.setUrlFile(updatedFile.getUrlFile());
            fileRepository.save(existingFile);
            return existingFile;
        }
    }

    @Override
    public void deleteFile(Long id) {
        Optional<file> optionalFile = fileRepository.findById(id);
        if (optionalFile.isEmpty()) {
            throw new FileNotFoundException("File with this id " + id + " not found");
        } else {
            fileRepository.deleteById(id);
        }
    }
    @Override
    public file addFile(file file) {
        return fileRepository.save(file);
    }



}
