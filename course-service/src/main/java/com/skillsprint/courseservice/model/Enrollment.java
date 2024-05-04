package com.skillsprint.courseservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "enrollments")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Enrollment {

    @Id
    private ObjectId id;
    private String courseId;
    private String userId;
}
