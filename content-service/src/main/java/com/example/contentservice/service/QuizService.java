package com.example.contentservice.service;

import com.example.contentservice.dto.QuizDTO;
import com.example.contentservice.dto.QuizResultDTO;
import com.example.contentservice.dto.UserAnswerDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {
    String addQuiz(QuizDTO quizDTO) ;
    QuizDTO getQuizById(String quizId);
    QuizDTO getQuizByModuleId(String moduleId);
    QuizDTO editQuiz(String quizId, QuizDTO updatedQuizDTO);
    void deleteQuizById(String quizId);
//    void deleteAllQuizzesByModuleId(String moduleId);
//    QuizResultDTO checkAnswer(UserAnswerDTO userAnswerDTO);
}
