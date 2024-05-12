package com.skillsprint.learnerservice.controllers;
import com.skillsprint.learnerservice.Service.EnrollmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course-enrollment")
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentController {

    @Autowired
    EnrollmentService enrollmentService;

    @PostMapping("/{courseId}")
    //@PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Object> courseEnrollment(@PathVariable String courseId,@RequestParam String courseName, @RequestHeader String userEmail, @RequestHeader String userRole){
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.courseEnrollment(courseId, userEmail, userRole,courseName));
    }

    @GetMapping("/user-courses")
    public ResponseEntity<Object> getCoursesByUserId(@RequestHeader String userEmail, @RequestHeader String userRole) {
        try {
            List<String> courseIds = enrollmentService.getCourseIdsByUserId(userEmail, userRole);
            return ResponseEntity.status(HttpStatus.OK).body(courseIds);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch courses: " + e.getMessage());
        }
    }


    @DeleteMapping("unenroll/{courseId}")
    public ResponseEntity<Object> courseUnenrollment(@PathVariable String courseId, @RequestHeader String userEmail, String userRole){
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.courseUnenrollment(courseId, userEmail, userRole));
    }


    //todo - My Enrollments
}
