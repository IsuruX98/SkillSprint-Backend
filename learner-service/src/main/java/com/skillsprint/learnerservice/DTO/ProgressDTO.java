package com.skillsprint.learnerservice.DTO;

import lombok.Data;

@Data
public class ProgressDTO {
    private String id;
    private String userId;
    private String courseId;
    private int noOfModules;
    private double percentage;
    private Boolean[] isDone;
}
