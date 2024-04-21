package com.example.efabackend.service.impl;
import com.example.efabackend.entity.quize;

import java.util.List;

import org.springframework.stereotype.Service;

public interface quizeService {
    List<quize> getAllQuize();
    quize getQuizeById(Long id);
    List<quize> findFilesByCourFile(String cour);
    quize updateQuize(Long id,String cour, String matiere, String question, int rep, String reponces);
    void deleteQuize(Long id);

    quize addQuize(quize quize);

}
