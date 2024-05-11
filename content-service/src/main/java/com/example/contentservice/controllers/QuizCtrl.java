package com.example.contentservice.controllers;

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
    public ResponseEntity<QuizDTO> addQuiz(@RequestBody QuizDTO quizDTO) {

        log.info("quiz DTO {}", (Object) quizDTO.getQuestions());

        QuizDTO addedQuiz = quizService.addQuiz(quizDTO);
        if (addedQuiz != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(addedQuiz);
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
    public ResponseEntity<List<QuizDTO>> getAllQuizzesByModuleId(@PathVariable String moduleId) {
        try{
            List<QuizDTO> quizzes = quizService.getAllQuizzesByModuleId(moduleId);
            return ResponseEntity.ok(quizzes);
        }catch (Exception e){
            log.error("Error in fetching All the quizzes of module {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
//
//    @DeleteMapping("/module/{moduleId}")
//    public ResponseEntity<Void> deleteAllQuizzesByModuleId(@PathVariable String moduleId) {
//        quizService.deleteAllQuizzesByModuleId(moduleId);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PostMapping("/check-answer")
//    public ResponseEntity<QuizResultDTO> checkAnswer(@RequestBody UserAnswerDTO userAnswerDTO) {
//        QuizResultDTO resultDTO = quizService.checkAnswer(userAnswerDTO);
//        return ResponseEntity.ok(resultDTO);
//    }
}
