package com.skillsprint.courseservice.controllers;

import com.skillsprint.courseservice.service.EnrollmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course-enrollment")
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentController {

    @Autowired
    EnrollmentService enrollmentService;

    @PostMapping("/{courseId}")
    //@PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Object> courseEnrollment(@PathVariable String courseId, @RequestHeader String userEmail, @RequestHeader String userRole){
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.courseEnrollment(courseId, userEmail, userRole));
    }
}

