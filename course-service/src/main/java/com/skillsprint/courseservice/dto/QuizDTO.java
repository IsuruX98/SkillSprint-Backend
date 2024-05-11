package com.skillsprint.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
    private String id;
    private String moduleId;
    private String description;
    private String title;
    private String question;
    private String[] options;
    private int correctOption;
}
