package com.example.contentservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerDTO {
    private String quizId;
    private String userId;
    private int[] answers;     // convert to int[] from string[]

}
