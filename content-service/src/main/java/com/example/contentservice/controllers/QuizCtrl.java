package com.example.contentservice.controllers;

import ch.qos.logback.core.net.ObjectWriter;
import com.example.contentservice.dto.QuizDTO;
import com.example.contentservice.dto.QuizResultDTO;
import com.example.contentservice.dto.UserAnswerDTO;
import com.example.contentservice.service.QuizService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quiz")
@AllArgsConstructor
@Slf4j
public class QuizCtrl {
    private final QuizService quizService;

    @PostMapping("/add")
    public ResponseEntity<Object> addQuiz(@RequestBody QuizDTO quizDTO) {

        log.info("quiz DTO {}", (Object) quizDTO.getQuestions());

        Object response = quizService.addQuiz(quizDTO);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable String quizId) {
        try{
            QuizDTO quizDTO = quizService.getQuizById(quizId);
            return ResponseEntity.ok(quizDTO);
        }catch (Exception e){
            log.error("Error in fetching single Quiz {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }// checked from postman
    }

    @GetMapping("/module/{moduleId}")
    public ResponseEntity<QuizDTO> getQuizByModuleId(@PathVariable String moduleId) {
        try {
            QuizDTO quiz = quizService.getQuizByModuleId(moduleId);

            if (quiz == null)
                return ResponseEntity.noContent().build();
            else
                return ResponseEntity.ok(quiz);

        } catch (Exception e) {
            log.error("Error in fetching All the quizzes of module {}", e.getMessage());
            throw e;
        }
    }

    @PutMapping("/{quizId}")
    public ResponseEntity<QuizDTO> editQuiz(@PathVariable String quizId, @RequestBody QuizDTO updatedQuizDTO) {
        try{
            QuizDTO editedQuiz = quizService.editQuiz(quizId, updatedQuizDTO);
            return ResponseEntity.ok(editedQuiz);
        }catch (Exception e){
            log.error("Error in Editing single Quiz  {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<Void> deleteQuizById(@PathVariable String quizId) {
        try{
            quizService.deleteQuizById(quizId);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            log.error("Error in Deleting single Quiz  {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
