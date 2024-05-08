package com.skillsprint.paymentservice.Ctrl;


import com.skillsprint.paymentservice.DTO.PaymentResDTO;
import com.skillsprint.paymentservice.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/payment-details")
@RequiredArgsConstructor
public class PaymentCtrl {
    private final PaymentService paymentService;

    //    @GetMapping("/course/{courseId}")
//    public ResponseEntity<List<PaymentResDTO>> getAllPaymentsForCourse(@PathVariable String courseId) {
//            List<PaymentResDTO> resDTOS = paymentService.getAllPaymentsForCourse(courseId);
//            return new ResponseEntity<>(resDTOS,HttpStatus.OK);
//    }
    @GetMapping("/course/{courseId}")
    public List<PaymentResDTO> getAllPaymentsForCourse(@PathVariable String courseId) {
        return paymentService.getAllPaymentsForCourse(courseId);
    }

    @GetMapping("/student/{studentId}")
    public List<PaymentResDTO> getAllPaymentsByStudent(@PathVariable String studentId) {
        return paymentService.getAllPaymentsByStudent(studentId);
    }
}
