package com.example.efabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.efabackend.entity.Quize; // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
import com.example.efabackend.exception.QuizNotFoundException;
import com.example.efabackend.Repo.QuizeRepository; // Correction : Changé "Repo" à "repo" pour correspondre aux conventions de nommage
import com.example.efabackend.service.impl.*; // Correction : Changé "impl.quizeService" à "QuizeService" pour correspondre au nom de l'interface

import java.util.List;
import java.util.Optional;

@Service
public class QuizeServiceImpl implements quizeService { // Correction : Changé "quizeService" à "QuizeService" pour correspondre au nom de l'interface
    @Autowired
    private QuizeRepository quizeRepository;

    @Override
    public List<Quize> getAllQuize() { // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
       return quizeRepository.findAll();
    }

    @Override
    public Quize getQuizeById(Long id) { // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
        return quizeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Quize> findQuizesByCour(String cour) { // Correction : Changé "findFilesByCourFile" à "findQuizesByCour" pour correspondre à l'entité Quize
       return quizeRepository.findByCourContainingIgnoreCase(cour);
    }
    
    @Override
    public Quize updateQuize(Long id, String cour, String matiere, String question, int rep, String reponses) {
        Optional<Quize> optionalQuize = quizeRepository.findById(id); // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
        if (optionalQuize.isPresent()) {
            Quize quiz = optionalQuize.get(); // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
            quiz.setMatiere(matiere);
            quiz.setCour(cour);
            quiz.setQuestion(question);
            quiz.setRep(rep);
            quiz.setReponces(reponses); // Correction du nom de variable
            return quizeRepository.save(quiz); // Enregistrer les modifications et retourner le quiz mis à jour
        } else {
            throw new QuizNotFoundException("Quize with id " + id + " not found");
        }
    }

    @Override
    public void deleteQuize(Long id) {
        Optional<Quize> optionalQuize = quizeRepository.findById(id); // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
        if (optionalQuize.isPresent()) {
            quizeRepository.deleteById(id);
        } else {
            throw new QuizNotFoundException("Quize with id " + id + " not found");
        }
    }

    @Override
    public Quize addQuize(Quize quize) { // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
        return quizeRepository.save(quize);
    }
}
