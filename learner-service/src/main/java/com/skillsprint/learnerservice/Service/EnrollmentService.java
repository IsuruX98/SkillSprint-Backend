package com.skillsprint.learnerservice.Service;

import com.skillsprint.learnerservice.dto.EnrollmentDTO;

import java.util.List;

public interface EnrollmentService {
    Object courseEnrollment(String courseId, String userName, String userRole,String courseName);

    void courseUnenrollment(String id);
    public List<EnrollmentDTO> getEnrollmentsByUserId(String userMail, String userRole);
}
