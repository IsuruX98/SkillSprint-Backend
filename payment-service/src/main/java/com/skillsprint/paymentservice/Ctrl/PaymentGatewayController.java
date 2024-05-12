package com.skillsprint.paymentservice.Ctrl;


import com.skillsprint.paymentservice.Client.StripeClient;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentGatewayController {
    private StripeClient stripeClient;

    @Autowired
    PaymentGatewayController(StripeClient stripeClient){
        this.stripeClient = stripeClient;
    }

    @PostMapping("/charge/{userId}/{courseId}/{courseName}/{userName}/{userEmail}/{userMobile}")
    public Charge chargeCard(
            @RequestHeader(value="token") String token,
            @RequestHeader(value="amount") Double amount,
            @PathVariable(value="userId") String userId,
            @PathVariable(value="courseId") String courseId,
            @PathVariable(value="courseName") String courseName,
            @PathVariable(value="userName") String userName,
            @PathVariable(value="userEmail") String userEmail,
            @PathVariable(value="userMobile") String userMobile
    ) throws Exception {
        return this.stripeClient.chargeNewCard(token, amount, courseName, userName, userId, courseId, userEmail, userMobile);
    }


}
