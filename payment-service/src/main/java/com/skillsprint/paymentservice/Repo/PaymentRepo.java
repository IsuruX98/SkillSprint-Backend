package com.skillsprint.paymentservice.Repo;

import com.skillsprint.paymentservice.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends MongoRepository<Payment,String> {

    List<Payment> findAllByCourseID(String courseId);

    List<Payment> findAllByUserID(String studentId);

    Payment findByCourseIDAndCourseID(String courseId, String studentId);
}