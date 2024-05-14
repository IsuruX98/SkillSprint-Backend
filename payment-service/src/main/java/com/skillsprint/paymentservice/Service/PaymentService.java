package com.skillsprint.paymentservice.Service;

import com.skillsprint.paymentservice.DTO.PaymentResDTO;

import java.util.List;

public interface PaymentService {

    PaymentResDTO addPayment(PaymentResDTO dto);
    List<PaymentResDTO> getAllPaymentsForCourse(String courseId);
    List<PaymentResDTO> getAllPaymentsByStudent(String studentId);
    PaymentResDTO getPaymentByCourseIdUserID(String courseId,String studentId);
    PaymentResDTO getPaymentById(int paymentId);

    List<PaymentResDTO> getAllPayments();

}
