package com.example.contentservice.controllers;

import com.example.contentservice.dto.QuizDTO;
import com.example.contentservice.dto.QuizResultDTO;
import com.example.contentservice.dto.UserAnswerDTO;
import com.example.contentservice.service.QuizService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course-controller")
@AllArgsConstructor
public class QuizCtrl {
    private final QuizService quizService;

    @PostMapping("/add")
    public ResponseEntity<QuizDTO> addQuiz(@RequestBody QuizDTO quizDTO) {
        QuizDTO addedQuiz = quizService.addQuiz(quizDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedQuiz);     // checked from postman
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable String quizId) {
        QuizDTO quizDTO = quizService.getQuizById(quizId);
        return ResponseEntity.ok(quizDTO);                   // checked from postman
    }

    @GetMapping("/module/{moduleId}")
    public List<QuizDTO> getAllQuizzesByModuleId(@PathVariable String moduleId) {
        return quizService.getAllQuizzesByModuleId(moduleId);
               // checked from postman
    }

    @PutMapping("/{quizId}")
    public ResponseEntity<QuizDTO> editQuiz(@PathVariable String quizId, @RequestBody QuizDTO updatedQuizDTO) {
        QuizDTO editedQuiz = quizService.editQuiz(quizId, updatedQuizDTO);
        return ResponseEntity.ok(editedQuiz);
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<Void> deleteQuizById(@PathVariable String quizId) {
        quizService.deleteQuizById(quizId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/module/{moduleId}")
    public ResponseEntity<Void> deleteAllQuizzesByModuleId(@PathVariable String moduleId) {
        quizService.deleteAllQuizzesByModuleId(moduleId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/check-answer")
    public ResponseEntity<QuizResultDTO> checkAnswer(@RequestBody UserAnswerDTO userAnswerDTO) {
        QuizResultDTO resultDTO = quizService.checkAnswer(userAnswerDTO);
        return ResponseEntity.ok(resultDTO);
    }
}
