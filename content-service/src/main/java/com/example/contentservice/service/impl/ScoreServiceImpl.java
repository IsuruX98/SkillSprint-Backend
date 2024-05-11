package com.example.contentservice.service.impl;

import com.example.contentservice.dto.UserAnswerDTO;
import com.example.contentservice.model.Quiz;
import com.example.contentservice.model.Score;
import com.example.contentservice.repository.QuizRepo;
import com.example.contentservice.repository.ScoreRepo;
import com.example.contentservice.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private ScoreRepo scoreRepo;

    @Override
    public Score calculateScoreAndSave(String userId, String quizId, UserAnswerDTO userAnswerDTO) {
        Quiz quiz = quizRepo.findById(quizId).orElse(null);
        if (quiz == null || quiz.getQuestions() == null) {
            throw new IllegalArgumentException("Quiz not found or no questions set");
        }
        int totalQuestions = quiz.getQuestions().length;
        if (userAnswerDTO.getAnswers().length != totalQuestions) {
            throw new IllegalArgumentException("Number of answers does not match the number of questions");
        }
        int correctAnswers = 0;
        for (int i = 0; i < totalQuestions; i++) {
            if (userAnswerDTO.getAnswers()[i] == quiz.getCorrectAnswers()[i]) {
                correctAnswers++;
            }
        }
        double scorePercentage = (double) correctAnswers / totalQuestions * 100;

        // Save the score to the database
        Score scoreEntity = new Score();
        scoreEntity.setUserId(userId);
        scoreEntity.setQuizId(quizId);
        scoreEntity.setScore((int) scorePercentage);
        return scoreRepo.save(scoreEntity);
    }
}
