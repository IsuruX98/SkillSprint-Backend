package com.example.contentservice.controller;

import com.example.contentservice.dto.UserAnswerDTO;
import com.example.contentservice.model.Score;
import com.example.contentservice.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/score")
@Slf4j
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping("/")
    public ResponseEntity<Score> calculateScoreAndSave(
            @RequestBody UserAnswerDTO userAnswerDTO) {
        try {
            Score score = scoreService.calculateScoreAndSave(userAnswerDTO.getUserId(), userAnswerDTO.getQuizId(), userAnswerDTO);
            log.info("Score of the Student {} is {}",score.getUserId(),score);

            return ResponseEntity.ok(score);
        } catch (IllegalArgumentException e) {
            log.info("Error in calling arguments {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            log.info("Error in calling API {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
