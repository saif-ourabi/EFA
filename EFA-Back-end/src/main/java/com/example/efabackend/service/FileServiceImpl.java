package com.example.efabackend.service;

import com.example.efabackend.Repo.FileRepository;
import com.example.efabackend.entity.File;
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
    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    public File getFileById(Long id) {
        return fileRepository.findById(id).orElse(null);
    }

    @Override
    public List<File> findFilesByNameFile(String nameFile) {
        return fileRepository.findBynameFileContainingIgnoreCase(nameFile);
    }

    @Override
    public File updateFile(Long id, String nameFile, String imgFile, byte[] urlFile) {
        Optional<File> optionalFile = fileRepository.findById(id);
        if (optionalFile.isEmpty()) {
            throw new FileNotFoundException("File with this id " + id + " not found");
        } else {
            File file = optionalFile.get();
            file.setNameFile(nameFile);
            file.setImgFile(imgFile);
            file.setUrlFile(urlFile);
            fileRepository.save(file);
            return file;
        }
    }

    @Override
    public void deleteFile(Long id) {
        Optional<File> optionalFile = fileRepository.findById(id);
        if (optionalFile.isEmpty()) {
            throw new FileNotFoundException("File with this id " + id + " not found");
        } else {
            fileRepository.deleteById(id);
        }
    }
    @Override
    public File addFile(File file) {
        return fileRepository.save(file);
    }

}
