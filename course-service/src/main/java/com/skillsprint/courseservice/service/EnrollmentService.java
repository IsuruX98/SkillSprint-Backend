package com.skillsprint.courseservice.service;

public interface EnrollmentService {
    Object courseEnrollment(String courseId, String userName, String userRole);

    Object courseUnenrollment(String courseId, String userEmail, String userRole);
}
