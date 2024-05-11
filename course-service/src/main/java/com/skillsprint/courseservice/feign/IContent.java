package com.skillsprint.courseservice.feign;


import com.skillsprint.courseservice.dto.QuizDTO;
import com.skillsprint.courseservice.dto.ReadingDTO;
import com.skillsprint.courseservice.dto.VideoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("CONTENT-SERVICE")
public interface IContent {

    @GetMapping("/api/v1/course-controller/module/{moduleId}")
    List<QuizDTO> getAllQuizzesByModuleId(@PathVariable String moduleId);

    @GetMapping("api/v1/reading-controller/reading-module/{moduleId}")
    List<ReadingDTO> getAllReadingsByModule(@PathVariable String moduleId);

    @GetMapping("/api/v1/content-controller/videos/{moduleId}")
    List<VideoDTO> getAllVideos(@PathVariable String moduleId);
}
