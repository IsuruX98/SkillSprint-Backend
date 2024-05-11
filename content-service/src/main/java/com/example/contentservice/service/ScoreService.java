package com.example.contentservice.service;

import com.example.contentservice.dto.UserAnswerDTO;
import com.example.contentservice.model.Score;

public interface ScoreService {
    public Score calculateScoreAndSave(String userId, String quizId, UserAnswerDTO userAnswerDTO);
}
