package com.skillsprint.learnerservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentAllDTO {
    private String id;
    private String courseId;
    private String userId;
}
