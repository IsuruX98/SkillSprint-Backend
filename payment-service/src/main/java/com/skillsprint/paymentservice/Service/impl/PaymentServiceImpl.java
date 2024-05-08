package com.skillsprint.paymentservice.Service.impl;

import com.skillsprint.paymentservice.DTO.PaymentResDTO;
import com.skillsprint.paymentservice.Repo.PaymentRepo;
import com.skillsprint.paymentservice.Service.PaymentService;
import com.skillsprint.paymentservice.model.Payment;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    public PaymentRepo repo;

    ModelMapper mapper =new ModelMapper();

    @Override
    public PaymentResDTO addPayment(PaymentResDTO dto) {
        try{
            Payment payment =mapper.map(dto,Payment.class);
            Payment savedPayment = repo.save(payment);
            log.info("Payment Done ! {}",savedPayment);
            return mapper.map(savedPayment,PaymentResDTO.class);
        }catch (Exception e){
            log.error("Error occured in adding {} ",e.getMessage());
            return null;
        }
    }

    @Override
    public List<PaymentResDTO> getAllPaymentsForCourse(String courseId) {
        try{
            List<Payment> payments = repo.findAllByCourseID(courseId);

            return payments.stream()
                    .map(payment -> mapper.map(payment, PaymentResDTO.class))
                    .collect(Collectors.toList());
        }catch (Exception e){
            log.error("Error Occurred in GET all payments of one course");
            return null;
        }
    }

    @Override
    public List<PaymentResDTO> getAllPaymentsByStudent(String studentId) {
        return null;
    }

    @Override
    public PaymentResDTO getPaymentById(int paymentId) {
        return null;
    }



}
