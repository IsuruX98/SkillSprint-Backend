package com.skillsprint.paymentservice.Client;

import com.skillsprint.paymentservice.DTO.PaymentResDTO;
import com.skillsprint.paymentservice.Service.PaymentService;
import com.skillsprint.paymentservice.Service.impl.PaymentServiceImpl;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
@Slf4j
@AllArgsConstructor
public class StripeClient {

    @Autowired
    PaymentService paymentService;


    StripeClient(){
        Stripe.apiKey = "sk_test_51PD0LQ01UQdk9J3Z7njmM3sqAyQwohqX3wNnI8Oyu6lPuovsjuNUJ2YVgIoL2kUGp5LoWSDHUUzcnwZIEs0NDo3X00p6cTjYAx";
    }

    public Customer createCustomer(String token, String email) throws Exception {
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("email", email);
        customerParams.put("source", token);
        return Customer.create(customerParams);
    }
    private Customer getCustomer(String id) throws Exception {
        return Customer.retrieve(id);
    }
    public Charge chargeNewCard(String token, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);

        Random random = new Random();

        LocalDateTime currentTime = LocalDateTime.now();

        paymentService.addPayment(new PaymentResDTO((random.nextInt()),"1234","c123", (long) amount,charge.getId(),charge.getPaid(),currentTime.toString()));
        return charge;
    }
    public Charge chargeCustomerCard(String customerId, int amount) throws Exception {
        String sourceCard = getCustomer(customerId).getDefaultSource();
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", "USD");
        chargeParams.put("customer", customerId);
        chargeParams.put("source", sourceCard);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }


}
