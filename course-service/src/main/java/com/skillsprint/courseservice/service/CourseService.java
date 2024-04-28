package com.skillsprint.courseservice.service;

import com.skillsprint.courseservice.dto.CourseDTO;

public interface CourseService {
    Object addCourse(CourseDTO courseDTO);

    CourseDTO getCourseByCourseCode(String courseCode);

    Object updateCourseByCourseCode(String courseCode, CourseDTO courseDTO);

    Object deleteCourseByCourseCode(String courseCode);
}
