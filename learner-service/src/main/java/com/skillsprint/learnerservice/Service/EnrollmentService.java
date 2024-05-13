package com.skillsprint.learnerservice.Service;

import com.skillsprint.learnerservice.dto.EnrollmentAllDTO;
import com.skillsprint.learnerservice.dto.EnrollmentDTO;

import java.util.List;

public interface EnrollmentService {
    Object courseEnrollment(String courseId, String userName, String userRole,String courseName);

    void courseUnenrollment(String id);
     List<EnrollmentDTO> getEnrollmentsByUserId(String userMail, String userRole);

    List<EnrollmentAllDTO> getAllEnrollment();
}
