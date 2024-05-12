package com.skillsprint.paymentservice.Ctrl;


import com.skillsprint.paymentservice.Client.StripeClient;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/payment")
public class PaymentGatewayController {
    private StripeClient stripeClient;

    @Autowired
    PaymentGatewayController(StripeClient stripeClient){
        this.stripeClient = stripeClient;
    }

    @PostMapping("/charge")
    public Charge chargeCard(@RequestHeader(value="token") String token,
                             @RequestHeader(value="amount") Double amount,
                             @RequestHeader(value="courseName") String courseName,
                             @RequestHeader(value="userName") String userName,
                             @RequestHeader(value="userId") String userId,
                             @RequestHeader(value="courseId") String courseId,
                             @RequestHeader(value="userEmail") String userEmail,
                             @RequestHeader(value="userMobile") String userMobile
                             ) throws Exception {

        return this.stripeClient.chargeNewCard(token, amount,courseName,userName,userId,courseId,userEmail,userMobile);
    }

}
