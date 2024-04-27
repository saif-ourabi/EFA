package com.example.efabackend.Controller.filleController;

import com.example.efabackend.Dto.FileDto;
import com.example.efabackend.entity.File;
import com.example.efabackend.service.impl.fileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class filleController {
    @Autowired
    private fileService fs;

    @GetMapping
    public List<File> getAllFiles() {
        return fs.getAllFiles();
    }

    @GetMapping("/filename")
    public ResponseEntity<List<File>> findByFileName(@RequestParam(value = "nameFile") String nameFile) {
        List<File> files = fs.findFilesByNameFile(nameFile);
        if (files.isEmpty()) {
            throw new FileNotFoundException("File not found with name: " + nameFile);
        } else {
            return ResponseEntity.ok(files);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        File file = fs.getFileById(id);
        if (file == null) {
            throw new FileNotFoundException("File not found with id: " + id);
        } else {
            fs.deleteFile(id);
            return ResponseEntity.ok("File with id " + id + " has been deleted");
        }
    }

    @PutMapping("/{id}/{nameFile}/{imgFile}/{urlFile}")
    public ResponseEntity<File> updateFile(@PathVariable Long id, @PathVariable String nameFile, @PathVariable String imgFile, @PathVariable String urlFile) {
        File file = fs.getFileById(id);
        if (file == null) {
            throw new FileNotFoundException("File not found with id: " + id);
        } else {
            byte[] urlFileBytes = Base64.getDecoder().decode(urlFile);
            file = fs.updateFile(id, nameFile, imgFile, urlFileBytes);
            return ResponseEntity.ok(file);
        }
    }
    @PostMapping("/addFile")
    public ResponseEntity<File> addFile(@RequestBody FileDto file) {
        File f = new File(file.getNameFile(),file.getImgFile(),file.getUrlFile());
        File newFile = fs.addFile(f);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFile);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<String> handleFileNotFoundException(FileNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    class FileNotFoundException extends RuntimeException {
        public FileNotFoundException(String message) {
            super(message);
        }
    }
}
