package com.skillsprint.courseservice.service;

import com.skillsprint.courseservice.dto.CourseDTO;
import com.skillsprint.courseservice.dto.DetailedCourseDTO;
import com.skillsprint.courseservice.model.Course;
import com.skillsprint.courseservice.model.CourseWrapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
    Object addCourse(CourseWrapper coverImg);

    CourseDTO getCourseById(String courseId);

    String updateCourseByCourseId(CourseWrapper courseWrapper);

    Object deleteCourseByCourseId(String courseId);

    List<CourseDTO> getCoursesByCategoryCode(String categoryCode);

    Object approveCourse(String courseId,String userEmail);

    List<CourseDTO> findAllByInstructorId(String instructorId);

    List<CourseDTO> getAll();

    DetailedCourseDTO getAllDetailedCourses(String courseId);
}
