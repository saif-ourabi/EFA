package com.example.efabackend.service.impl; // Correction : Changé "impl" à "service" pour correspondre aux conventions de nommage

import com.example.efabackend.entity.Quize; // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
import java.util.List;

public interface quizeService { // Correction : Changé "quizeService" à "QuizeService" pour correspondre aux conventions de nommage
    List<Quize> getAllQuize();
    Quize getQuizeById(Long id); // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
    List<Quize> findQuizesByCour(String cour); // Correction : Changé "findFilesByCourFile" à "findQuizesByCour" pour correspondre à l'entité Quize
    Quize updateQuize(Long id, String cour, String matiere, String question, int rep, String reponses); // Correction : Changé "reponces" à "reponses" pour correspondre à la variable
    void deleteQuize(Long id);

    Quize addQuize(Quize quize);
}

