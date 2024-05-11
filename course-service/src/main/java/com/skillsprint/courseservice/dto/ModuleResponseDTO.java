package com.skillsprint.courseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleResponseDTO {

    private String id;
    private String moduleName;
    private String moduleCode;
    private String courseId;
    private List<VideoDTO> videoDTOList;
    private List<ReadingDTO> readingDTOList;
    private QuizDTO quizDTO;

}
