package com.skillsprint.paymentservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResDTO {
    private int paymentID;
    private String userID;
    private String courseID;
    private Long amount;
    private String stripeID;
    private Boolean isPaid;
    private String paidDate;
}
