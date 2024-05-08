package com.skillsprint.paymentservice.Repo;

import com.skillsprint.paymentservice.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepo extends MongoRepository<Payment,String> {

    List<Payment> findAllByCourseID(String courseId);
}
