package com.skillsprint.courseservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "unenrollments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnEnrollment {

    @Id
    private String id;
    private String userId;
    private String courseId;
    private String price;
    //TODO - need to add banking account Details???
}
