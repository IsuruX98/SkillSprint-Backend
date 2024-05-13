package com.skillsprint.learnerservice.Service.ServiceImpl;
import com.skillsprint.learnerservice.Service.EnrollmentService;
import com.skillsprint.learnerservice.dto.EmailBodyDTO;
import com.skillsprint.learnerservice.dto.EnrollmentDTO;
import com.skillsprint.learnerservice.dto.MessageDTO;
import com.skillsprint.learnerservice.dto.UserDTO;
import com.skillsprint.learnerservice.feing.IEnrollment;
import com.skillsprint.learnerservice.feing.INotification;
import com.skillsprint.learnerservice.model.Enrollment;
import com.skillsprint.learnerservice.repository.EnrollmentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
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

             userDTO = iEnrollment.getUserByEmail(userName, userRole); //get user by calling getUserBy method in User Service

            Enrollment enrollment = new Enrollment();
            enrollment.setCourseId(courseId);
            enrollment.setUserId(userDTO.getUserId());
            enrollmentRepository.save(enrollment);

            //send mail
            emailBodyDTO.setTo(userDTO.getEmail());
            emailBodyDTO.setMsg("Dear " + userDTO.getUserName() + ",\n\n" +
                    "Congratulations! You have successfully enrolled in the "+courseName+ " Course.\n\n" +
                    "Thank you for choosing SkillSprint.\n\n" +
                    "Best regards,\n" +
                    "SkillSprint Team");

            emailBodyDTO.setSubject("SkillSprint Course Enrollment");

            //send message
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
    public void courseUnenrollment(String id) throws IllegalArgumentException {
        try {
            if (enrollmentRepository.existsById(id)) {
                enrollmentRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("Enrollment not available");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }


    @Override
    public List<EnrollmentDTO> getEnrollmentsByUserId(String userMail, String userRole) {
        try {
            userDTO = iEnrollment.getUserByEmail(userMail, userRole);
            List<Enrollment> enrollments = enrollmentRepository.findByUserId(userDTO.getUserId());

            // Map Enrollment objects to EnrollmentDTO objects
            List<EnrollmentDTO> enrollmentDTOs = enrollments.stream()
                    .map(enrollment -> new EnrollmentDTO(enrollment.getId().toString(), enrollment.getCourseId()))
                    .collect(Collectors.toList());

            return enrollmentDTOs;
        } catch (Exception e) {
            log.error("Error occurred while fetching enrollments by user ID: {}", e.getMessage());
            throw e;
        }
    }




}
