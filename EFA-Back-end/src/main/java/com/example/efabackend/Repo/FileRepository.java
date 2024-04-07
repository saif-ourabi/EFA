package com.example.efabackend.Repo;

import com.example.efabackend.entity.file;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<file, Long> {
    List<file> findBynameFileContainingIgnoreCase(String nameFile);
}
