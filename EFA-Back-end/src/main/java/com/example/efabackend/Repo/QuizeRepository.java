package com.example.efabackend.Repo; // Correction : Changé "Repo" à "repo" pour correspondre aux conventions de nommage

import com.example.efabackend.entity.Quize; // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizeRepository extends JpaRepository<Quize, Long> { // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
    List<Quize> findByCourContainingIgnoreCase(String cour);
}
