package com.example.contentservice.service;

import com.example.contentservice.dto.QuizDTO;
import com.example.contentservice.dto.QuizResultDTO;
import com.example.contentservice.dto.UserAnswerDTO;

import java.util.List;

public interface QuizService {
    QuizDTO addQuiz(QuizDTO quizDTO);
    QuizDTO getQuizById(String quizId);
    List<QuizDTO> getAllQuizzesByModuleId(String moduleId);
    QuizDTO editQuiz(String quizId, QuizDTO updatedQuizDTO);
    void deleteQuizById(String quizId);
    void deleteAllQuizzesByModuleId(String moduleId);
    QuizResultDTO checkAnswer(UserAnswerDTO userAnswerDTO);
}
