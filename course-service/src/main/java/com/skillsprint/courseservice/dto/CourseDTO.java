package com.skillsprint.courseservice.dto;

import com.skillsprint.courseservice.model.Module;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private ObjectId id;
    private String courseCode;
    private String courseName;
//    private List<Module> moduleList;
    private String categoryId;
    private String description;
//    private List<String> instructorIds;
    private BigDecimal price;
    private String status;
}
