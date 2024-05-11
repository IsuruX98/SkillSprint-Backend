package com.skillsprint.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailedCourseDTO {

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
    private List<ModuleResponseDTO> moduleResponseDTOList;


}
