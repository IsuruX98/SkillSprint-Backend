package com.example.contentservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResultDTO {
    private String quizId;
    private String userId;
    private Boolean status;
    private int correctOption;
}
