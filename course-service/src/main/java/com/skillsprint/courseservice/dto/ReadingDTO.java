package com.skillsprint.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadingDTO {
    private String id;
    private String title;
    private String description;
    private String duration;
    private String moduleId;
}
