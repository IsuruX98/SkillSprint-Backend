package com.skillsprint.courseservice.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "courses")
@EqualsAndHashCode(callSuper = true)
public class Course extends CommonEntity {

    @Id
    private ObjectId id;
    private String courseCode;
    private String courseName;
    private String categoryId;
    private String description;
    private BigDecimal price;
    private String status;

}
