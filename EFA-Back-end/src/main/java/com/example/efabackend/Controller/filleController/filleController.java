package com.example.efabackend.Controller.filleController;

import com.example.efabackend.Dto.FileDto;
import com.example.efabackend.entity.file;
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
    public List<file> getAllFiles() {
        return fs.getAllFiles();
    }

    @GetMapping("/filename")
    public ResponseEntity<List<file>> findByFileName(@RequestParam(value = "nameFile") String nameFile) {
        List<file> files = fs.findFilesByNameFile(nameFile);
        if (files.isEmpty()) {
            throw new FileNotFoundException("File not found with name: " + nameFile);
        } else {
            return ResponseEntity.ok(files);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        file file = fs.getFileById(id);
        if (file == null) {
            throw new FileNotFoundException("File not found with id: " + id);
        } else {
            fs.deleteFile(id);
            return ResponseEntity.ok("File with id " + id + " has been deleted");
        }
    }

    @PutMapping("/updateFile/{id}")
    public ResponseEntity<file> updateFile(@PathVariable Long id, @RequestBody FileDto updatedFileDto) {
        file existingFile = fs.getFileById(id);
        if (existingFile == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        existingFile.setNameFile(updatedFileDto.getNameFile());
        existingFile.setImgFile(updatedFileDto.getImgFile());

        // Assuming urlFile is sent as a base64 encoded string
        existingFile.setUrlFile(Base64.getDecoder().decode(updatedFileDto.getUrlFile()));

        file updatedEntity = fs.updateFile(existingFile);
        return ResponseEntity.ok(updatedEntity);
    }


    @PostMapping("/addFile")
    public ResponseEntity<file> addFile(@RequestBody FileDto file) {
        file f = new file(file.getNameFile(),file.getImgFile(),file.getUrlFile());
        file newFile = fs.addFile(f);
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
