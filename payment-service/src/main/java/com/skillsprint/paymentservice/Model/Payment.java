package com.skillsprint.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payments")
public class Payment {
    @Id
    private Integer paymentID;
    private String userID;
    private String courseID;
    private String courseName;
    private String studentName;
    private Long amount;
    private String stripeID;
    private Boolean isPaid;
    private String paidDate;
}
