package com.example.contentservice.dto;

import com.example.contentservice.model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
    private String id;
    private String moduleId;
    private String description;
    private String title;
    private Question[] questions;

    public void setCorrectAnswers(int[] correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    private int[] correctAnswers;
    private Boolean isPassed;
}
