package com.skillsprint.learnerservice.Service.ServiceImpl;
import com.skillsprint.learnerservice.Service.EnrollmentService;
import com.skillsprint.learnerservice.dto.EmailBodyDTO;
import com.skillsprint.learnerservice.dto.MessageDTO;
import com.skillsprint.learnerservice.dto.UserDTO;
import com.skillsprint.learnerservice.feing.IEnrollment;
import com.skillsprint.learnerservice.feing.INotification;
import com.skillsprint.learnerservice.model.Enrollment;
import com.skillsprint.learnerservice.repository.EnrollmentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    INotification iNotification;

    @Autowired
    IEnrollment iEnrollment;

    @Autowired
    EmailBodyDTO emailBodyDTO;
    @Autowired
    MessageDTO messageDTO;
    @Autowired
    UserDTO userDTO;

    private final ExecutorService executorService = Executors.newFixedThreadPool(2); // Create a thread pool with 2 threads for concurrent execution

    @Override
    @Transactional
    public Object courseEnrollment(String courseId, String userName, String userRole, String courseName) {
        try{

             userDTO = iEnrollment.getUserByEmail(userName, userRole);

            Enrollment enrollment = new Enrollment();
            enrollment.setCourseId(courseId);
            enrollment.setUserId(userDTO.getUserId());
            enrollmentRepository.save(enrollment);

            emailBodyDTO.setTo(userDTO.getEmail());
            emailBodyDTO.setMsg("Dear " + userDTO.getUserName() + ",\n\n" +
                    "Congratulations! You have successfully enrolled in the "+courseName+ " Course.\n\n" +
                    "Thank you for choosing SkillSprint.\n\n" +
                    "Best regards,\n" +
                    "SkillSprint Team");

            emailBodyDTO.setSubject("SkillSprint Course Enrollment");

            messageDTO.setNumber(userDTO.getContactNo());
            messageDTO.setMessageBody("Dear " + userDTO.getUserName() + ",\n\n" +
                    " You have successfully enrolled in the "+courseName+ "Course.\n\n" +
                    "Best regards,\n" +
                    "SkillSprint Team");

            executorService.submit(() -> iNotification.sendEmail(emailBodyDTO)); // Submit email sending task to executor service
            executorService.submit(() -> iNotification.sendSms(messageDTO)); // Submit SMS sending task to executor service

            return ResponseEntity.status(HttpStatus.OK).body("Successfully enrolled in the course");

        } catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Object courseUnenrollment(String courseId, String userEmail, String userRole) {
        try{
            return null;
        } catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    public List<String> getCourseIdsByUserId(String userMail,String userRole) {
        try {

            userDTO = iEnrollment.getUserByEmail(userMail, userRole);
            List<Enrollment> enrollments = enrollmentRepository.findByUserId(userDTO.getUserId());

            // Extract course IDs from the list of enrollments
            List<String> courseIds = enrollments.stream()
                    .map(Enrollment::getCourseId)
                    .collect(Collectors.toList());

            return courseIds;
        } catch (Exception e) {
            log.error("Error occurred while fetching course IDs by user ID: {}", e.getMessage());
            throw e;
        }
    }




}
