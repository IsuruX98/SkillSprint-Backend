package com.skillsprint.learnerservice.feing;

import com.skillsprint.learnerservice.DTO.ModuleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("COURSE-SERVICE")
public interface CourseFeing {

    @GetMapping("/{courseId}")
    ResponseEntity<List<ModuleDTO>> getModulesByCourseId(@PathVariable String courseId);
}
