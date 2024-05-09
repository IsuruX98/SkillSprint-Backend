package com.skillsprint.learnerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Progresses")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Progress {
    @Id
    private String id;
    private String userId;
    private String courseId;
    private int noOfModules;
    private double percentage;
    private Boolean[] isDone;
}
