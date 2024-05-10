package com.skillsprint.courseservice.service.impl;

import com.skillsprint.courseservice.feign.IUser;
import com.skillsprint.courseservice.model.Enrollment;
import com.skillsprint.courseservice.repository.EnrollmentRepository;
import com.skillsprint.courseservice.service.EnrollmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    IUser iEnrollment;


    @Override
    @Transactional
    public Object courseEnrollment(String courseId, String userName, String userRole) {
        try{

            String uName = iEnrollment.getUserByEmail(userName, userRole).getBody();

            Enrollment enrollment = new Enrollment();
            enrollment.setCourseId(courseId);
            enrollment.setUserId(uName);
            enrollmentRepository.save(enrollment);

            return ResponseEntity.status(HttpStatus.OK).body("Successfully enroll to the course");

        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Object courseUnenrollment(String courseId, String userEmail, String userRole) {
        try{
            return null;  //todo - course Unenrollment
        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }
}
