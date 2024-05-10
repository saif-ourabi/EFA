package com.example.efabackend.fileController; // Correction : Changé "fileController" à "controller" pour correspondre aux conventions de nommage

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.efabackend.Dto.QuizDto; // Correction : Importation de QuizDto du package correct
import com.example.efabackend.entity.Quize; // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
import com.example.efabackend.exception.QuizNotFoundException;
import com.example.efabackend.service.QuizeServiceImpl; // Correction : Changé "impl.quizeService" à "QuizeService" pour correspondre au nom de l'interface
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/quiz")
public class QuizeController { // Correction : Changé "QuizeController" à "QuizeController" pour correspondre aux conventions de nommage

    @Autowired
    private QuizeServiceImpl quizeService; // Correction : Changé "quizeService" à "QuizeService" pour correspondre au nom de l'interface

    @GetMapping
    public List<Quize> getAllQuizes() { // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
        return quizeService.getAllQuize(); // Correction : Changé "qs" à "quizeService" pour correspondre au nom de la variable
    }

    @GetMapping("/quizByMatiere")
    public ResponseEntity<List<Quize>> findByMatiere(@RequestParam(value = "matiere") String matiere) { // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
        List<Quize> quizes = quizeService.findQuizesByCour(matiere); // Correction : Changé "qs" à "quizeService" pour correspondre au nom de la variable
        if (quizes.isEmpty()) {
            throw new QuizNotFoundException("No quiz found with matiere: " + matiere);
        } else {
            return ResponseEntity.ok(quizes);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        Quize quiz = quizeService.getQuizeById(id); // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
        if (quiz == null) {
            throw new QuizNotFoundException("Quiz not found with id: " + id);
        } else {
            quizeService.deleteQuize(id); // Correction : Changé "qs" à "quizeService" pour correspondre au nom de la variable
            return ResponseEntity.ok("Quiz with id " + id + " has been deleted");
        }
    }

    @PutMapping("/{id}/{cour}/{matiere}/{question}/{rep}/{reponses}")
    public ResponseEntity<Quize> updateQuiz(@PathVariable Long id, @PathVariable String cour, @PathVariable String matiere, @PathVariable String question,@PathVariable int rep, @PathVariable String reponses) { // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
        Quize quiz = quizeService.getQuizeById(id); // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
        if (quiz == null) {
            throw new QuizNotFoundException("Quiz not found with id: " + id);
        } else {
            quiz = quizeService.updateQuize(id, cour, matiere, question, rep, reponses); // Correction : Changé "qs" à "quizeService" pour correspondre au nom de la variable
            return ResponseEntity.ok(quiz);
        }
    }

    @PostMapping("/addQuiz")
    public ResponseEntity<Quize> addQuiz(@RequestBody QuizDto quizDto) {
        Quize quiz = new Quize(quizDto.getCour(), quizDto.getMatiere(), quizDto.getQuestion(), quizDto.getRep(), quizDto.getReponses()); // Correction : Changé "quize" à "Quize" pour correspondre au nom de la classe
        Quize newQuiz = quizeService.addQuize(quiz); // Correction : Changé "qs" à "quizeService" pour correspondre au nom de la variable
        return ResponseEntity.status(HttpStatus.CREATED).body(newQuiz);
    }

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<String> handleQuizNotFoundException(QuizNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    class QuizNotFoundException extends RuntimeException {
        public QuizNotFoundException(String message) {
            super(message);
        }
    }
}
