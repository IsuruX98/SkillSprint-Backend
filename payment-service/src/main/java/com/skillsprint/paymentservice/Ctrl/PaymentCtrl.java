package com.skillsprint.paymentservice.Ctrl;


import com.skillsprint.paymentservice.DTO.PaymentResDTO;
import com.skillsprint.paymentservice.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment-details")
@RequiredArgsConstructor
@Slf4j
public class PaymentCtrl {
    private final PaymentService paymentService;


    @GetMapping("/course/{courseId}")
    public List<PaymentResDTO> getAllPaymentsForCourse(@PathVariable String courseId) {
        return paymentService.getAllPaymentsForCourse(courseId);
    }

    @GetMapping("/student/{studentId}")
    public List<PaymentResDTO> getAllPaymentsByStudent(@PathVariable String studentId) {
        return paymentService.getAllPaymentsByStudent(studentId);
    }

    @GetMapping("/course/{courseId}/user/{studentId}")
    public ResponseEntity<PaymentResDTO> getPaymentByCourseIdAndUserId(
            @PathVariable("courseId") String courseId,
            @PathVariable("studentId") String studentId) {

        PaymentResDTO payment = paymentService.getPaymentByCourseIdUserID(courseId, studentId);

        if (payment != null) {
            log.info("Done Payment");
            return ResponseEntity.ok(payment);
        } else {
            log.info("Error in payment");
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PaymentResDTO>> getAllPayments() {
        List<PaymentResDTO> payments = paymentService.getAllPayments();
        if (payments != null) {
            return ResponseEntity.ok(payments);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
