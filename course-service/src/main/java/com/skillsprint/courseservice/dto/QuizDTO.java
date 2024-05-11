package com.skillsprint.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO{
    @Id
    private String id;
    private String description;
    private String title;
    private String moduleId;
    private QuestionDTO[] questions;
    private int[] correctAnswers;
    private Boolean isPassed;
}
