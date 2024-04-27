package com.skillsprint.courseservice.controllers;

import com.skillsprint.courseservice.dto.CourseDTO;
import com.skillsprint.courseservice.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course-controller")
@AllArgsConstructor
@NoArgsConstructor
public class CourseController {


    @Autowired
    CourseService courseService;

    @PostMapping("")
  //@PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Object> addCourse(@RequestBody CourseDTO courseDTO){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.addCourse(courseDTO));
    }

    @GetMapping("/{courseCode}")
   // @PreAuthorize("hasAnyAuthority('admin:read', 'faculty:read', 'student:read')")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable String courseCode){
        CourseDTO courseDTO = courseService.getCourseByCourseCode(courseCode);
        if(courseDTO != null)
            return ResponseEntity.status(HttpStatus.OK).body(courseDTO);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{courseCode}")
   // @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Object> updateCourseByCourseCode(@PathVariable String courseCode, @RequestBody CourseDTO courseDTO){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.updateCourseByCourseCode(courseCode, courseDTO));
    }


    @PutMapping("/delete/{courseCode}")
 //   @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Object> deleteCourseByCourseCode(@PathVariable String courseCode){
        return ResponseEntity.status(HttpStatus.OK).body(courseService.deleteCourseByCourseCode(courseCode));
    }


}
