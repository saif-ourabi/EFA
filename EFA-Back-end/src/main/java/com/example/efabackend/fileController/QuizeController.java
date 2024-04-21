package com.example.efabackend.fileController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.efabackend.Dto.QuizDto;
import com.example.efabackend.entity.quize;
import com.example.efabackend.exception.QuizNotFoundException;
import com.example.efabackend.service.impl.quizeService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/quiz")
public class QuizeController {

    @Autowired
    private quizeService qs;

    @GetMapping
    public List<quize> getAllQuizes() {
        return qs.getAllQuize();
    }

    @GetMapping("/quizByMatiere")
    public ResponseEntity<List<quize>> findByMatiere(@RequestParam(value = "matiere") String matiere) {
        List<quize> quizes = qs.findFilesByCourFile(matiere);
        if (quizes.isEmpty()) {
            throw new QuizNotFoundException("No quiz found with matiere: " + matiere);
        } else {
            return ResponseEntity.ok(quizes);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        quize quiz = qs.getQuizeById(id);
        if (quiz == null) {
            throw new QuizNotFoundException("Quiz not found with id: " + id);
        } else {
            qs.deleteQuize(id);
            return ResponseEntity.ok("Quiz with id " + id + " has been deleted");
        }
    }

    @PutMapping("/{id}/{cour}/{matiere}/{question}/{rep}/{reponses}")
    public ResponseEntity<quize> updateQuiz(@PathVariable Long id, @PathVariable String cour, @PathVariable String matiere, @PathVariable String question,@PathVariable int rep, @PathVariable String reponses) {
        quize quiz = qs.getQuizeById(id);
        if (quiz == null) {
            throw new QuizNotFoundException("Quiz not found with id: " + id);
        } else {
            quiz = qs.updateQuize(id, cour, matiere, question, rep, reponses);
            return ResponseEntity.ok(quiz);
        }
    }

    @PostMapping("/addQuiz")
    public ResponseEntity<quize> addQuiz(@RequestBody QuizDto quizDto) {
        quize quiz = new quize(quizDto.getCour(), quizDto.getMatiere(), quizDto.getQuestion(), quizDto.getRep(), quizDto.getReponces());
        quize newQuiz = qs.addQuize(quiz);
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
