package com.skillsprint.learnerservice.controllers;
import com.skillsprint.learnerservice.Service.EnrollmentService;
import com.skillsprint.learnerservice.dto.EnrollmentAllDTO;
import com.skillsprint.learnerservice.dto.EnrollmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course-enrollment")  // Endpoint for course-enrollment
public class EnrollmentController {

    @Autowired
    EnrollmentService enrollmentService; // Service dependency injection

    @PostMapping("/{courseId}")
    //@PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Object> courseEnrollment(@PathVariable String courseId,@RequestParam String courseName, @RequestHeader String userEmail, @RequestHeader String userRole){
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentService.courseEnrollment(courseId, userEmail, userRole,courseName));
    }

    @GetMapping("/user-courses")
    public ResponseEntity<Object> getCoursesByUserId(@RequestHeader String userEmail, @RequestHeader String userRole) {
        try {
            List<EnrollmentDTO> enrollments = enrollmentService.getEnrollmentsByUserId(userEmail, userRole);
            return ResponseEntity.status(HttpStatus.OK).body(enrollments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch courses: " + e.getMessage());
        }
    }


    @DeleteMapping("unenroll/{id}")
    public ResponseEntity<Object> courseUnenrollment(@PathVariable String id){
        try {
            enrollmentService.courseUnenrollment(id);
            return ResponseEntity.ok( "Successfully unrolled!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to unenroll ");
        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllEnrollment(@RequestHeader String userRole) {
        try {
            List<EnrollmentAllDTO> enrollments = enrollmentService.getAllEnrollment();
            return ResponseEntity.status(HttpStatus.OK).body(enrollments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch courses: " + e.getMessage());
        }
    }

}
