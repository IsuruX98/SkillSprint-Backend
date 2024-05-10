package com.skillsprint.courseservice.controllers;

import com.skillsprint.courseservice.dto.CourseDTO;
import com.skillsprint.courseservice.model.CourseWrapper;
import com.skillsprint.courseservice.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/course-controller")
@AllArgsConstructor
@NoArgsConstructor
public class CourseController {


    @Autowired
    CourseService courseService;

    @PostMapping(value = "", consumes = "multipart/form-data")
  //@PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Object> addCourse(@ModelAttribute CourseWrapper courseWrapper){
        Object result = courseService.addCourse(courseWrapper);

        if (result instanceof String) {
            String message = (String) result;
            if (message.equals("Course Added Successfully")) {
                return ResponseEntity.status(HttpStatus.OK).body(result);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }
        } else {
            // Handle unexpected return type
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected response from service");
        }
    }

    @GetMapping("/{courseId}")
   // @PreAuthorize("hasAnyAuthority('admin:read', 'faculty:read', 'student:read')")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable String courseId){
        CourseDTO courseDTO = courseService.getCourseById(courseId);
        if(courseDTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(courseDTO);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    //@TODO - not completed......
    @PutMapping("/")
    public ResponseEntity<Object> updateCourseByCourseCode(@RequestBody CourseDTO courseDTO) {
        Object result = courseService.updateCourseByCourseId(courseDTO);

        if (result instanceof String) {
            String message = (String) result;
            if (message.equals("Course Updated Successfully")) {
                return ResponseEntity.status(HttpStatus.OK).body(result);
            } else if (message.equals("Course not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
            }
        } else {
            // Handle unexpected return type
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected response from service");
        }
    }


    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<Object> deleteCourseByCourseCode(@PathVariable String courseId) {
        Object result = courseService.deleteCourseByCourseId(courseId);

        if (result instanceof String) {
            String message = (String) result;
            if (message.equals("Course Deleted Successfully")) {
                return ResponseEntity.status(HttpStatus.OK).body(result);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected response from service");
        }
    }


    //TODO - Test this API - not tested yet.

    @GetMapping("category/{courseCode}")
    // @PreAuthorize("hasAnyAuthority('admin:read', 'faculty:read', 'student:read')")
    public ResponseEntity<List<CourseDTO>> getCoursesByCategoryCode(@PathVariable String categoryCode){
        List<CourseDTO> courseDTOs = courseService.getCoursesByCategoryCode(categoryCode);
        if(courseDTOs != null)
            return ResponseEntity.status(HttpStatus.OK).body(courseDTOs);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @PutMapping("/{courseId}")
    public ResponseEntity<Object> approveCourseByCourseId(@PathVariable String courseId, @RequestHeader String userRole, @RequestHeader String userEmail) {
        try {
            Object result;
            if ("admin".equals(userRole)) {
                result = courseService.approveCourse(courseId,userEmail);
                if (result.equals("Course Approved")) {
                    return ResponseEntity.status(HttpStatus.OK).body(result);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to approve course");
        }
    }


    @GetMapping("instructor/{instructorId}")
    public ResponseEntity<List<CourseDTO>> getAllCoursesByInstructorId(@PathVariable String instructorId){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAllByInstructorId(instructorId));
    }


    @GetMapping("all/")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getAll());
    }


}
