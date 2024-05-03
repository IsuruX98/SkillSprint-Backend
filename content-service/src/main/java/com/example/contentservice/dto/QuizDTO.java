package com.example.contentservice.dto;

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
    private String question;
    private String[] options;
    private int correctOption;
}
