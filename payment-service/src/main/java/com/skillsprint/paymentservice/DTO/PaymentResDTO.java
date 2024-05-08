package com.skillsprint.paymentservice.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResDTO {
    private Integer paymentID = 0;
    private String userID;
    private String courseID;
    private Long amount;
    private String stripeID;
    private Boolean isPaid;
    private String paidDate;
}
