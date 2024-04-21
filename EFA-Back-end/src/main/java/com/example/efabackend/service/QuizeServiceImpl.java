package com.example.efabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.efabackend.exception.QuizNotFoundException;
import com.example.efabackend.Repo.QuizeRepository;
import com.example.efabackend.entity.quize;
import com.example.efabackend.service.impl.quizeService;

import java.util.List;
import java.util.Optional;
@Service
public class QuizeServiceImpl implements quizeService {
    @Autowired
    private QuizeRepository quizeRepository;

   

    @Override
    public List<quize> getAllQuize() {
       return quizeRepository.findAll();
    }

    @Override
    public quize getQuizeById(Long id) {
        return quizeRepository.findById(id).orElse(null);
    }

    @Override
    public List<quize> findFilesByCourFile(String cour) {
       return quizeRepository.findByCourContainingIgnoreCase(cour);
    }

    @Override
    public quize updateQuize(Long id,String cour, String matiere, String question, int rep, String reponces) {
       Optional<quize>optionalQuize=quizeRepository.findById(id);
       if(optionalQuize.isEmpty()){
        throw new QuizNotFoundException("quize with this id"+id+"not found");
       }else{
        quize quize=optionalQuize.get();
        quize.setMatiere(matiere);
        quize.setCour(cour);
        quize.setQuestion(question);
        quize.setRep(rep);
        quize.setReponces(reponces);
        return quize;
       }
    }

    @Override
    public void deleteQuize(Long id) {
        Optional<quize>optionalQuize=quizeRepository.findById(id);
        if(optionalQuize.isEmpty()){
            throw new QuizNotFoundException("quize with this id"+id+"not found");
           }else{
            quizeRepository.deleteById(id);
           }
    }

    @Override
    public quize addQuize(quize quize) {
        return quizeRepository.save(quize);
    }
}
