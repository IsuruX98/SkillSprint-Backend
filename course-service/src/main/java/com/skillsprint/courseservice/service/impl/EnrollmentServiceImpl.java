package com.skillsprint.courseservice.service.impl;

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


    @Override
    @Transactional
    public Object courseEnrollment(String courseId, String userName) {
        try{

            Enrollment enrollment = new Enrollment();
            enrollment.setCourseId(courseId);
            enrollment.setUserName("setuserName");  //TODO - extract userName from user-service
            enrollmentRepository.save(enrollment);

            return ResponseEntity.status(HttpStatus.OK).body("Successfully enroll to the course");

        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }
}