package com.skillsprint.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTO {
    private String id;
    private String title;
    private String duration;
    private String url;
    private String moduleId;
}
