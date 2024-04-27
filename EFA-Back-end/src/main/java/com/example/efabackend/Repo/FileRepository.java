package com.example.efabackend.Repo;

import com.example.efabackend.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findBynameFileContainingIgnoreCase(String nameFile);
}
