package com.skillsprint.learnerservice.Service;

import java.util.List;

public interface EnrollmentService {
    Object courseEnrollment(String courseId, String userName, String userRole,String courseName);

    Object courseUnenrollment(String courseId, String userEmail, String userRole);
    List<String> getCourseIdsByUserId(String userMail, String userRole);
}
