package com.skillsprint.courseservice.service;

import com.skillsprint.courseservice.dto.CourseDTO;

import java.util.List;

public interface CourseService {
    Object addCourse(CourseDTO courseDTO);

    CourseDTO getCourseByCourseCode(String courseCode);

    Object updateCourseByCourseCode(String courseCode, CourseDTO courseDTO);

    Object deleteCourseByCourseCode(String courseCode);

    List<CourseDTO> getCoursesByCategoryCode(String categoryCode);
}
