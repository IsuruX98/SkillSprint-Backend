package com.skillsprint.courseservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseWrapper {

    private String id;
    private String courseName;
    private String categoryId;
    private String description;
    private BigDecimal price;
    private String level;
    private String[] skillgained;
    private String status;
    private String instructorId;
    private String coverImgUrl;
    private MultipartFile file;
}
