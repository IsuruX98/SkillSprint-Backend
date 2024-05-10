package com.example.contentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "quizes")
@AllArgsConstructor
@NoArgsConstructor
public class Quiz extends CommonEntity {
    @Id
    private String id;
    private String description;
    private String title;
    private String moduleId;
    private String question;
    private String[] options;
    private int correctOption;
}
