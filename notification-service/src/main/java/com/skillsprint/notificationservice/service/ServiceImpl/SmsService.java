package com.skillsprint.notificationservice.service.ServiceImpl;

import com.skillsprint.notificationservice.DTO.MessageDTO;
import com.skillsprint.notificationservice.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService implements com.skillsprint.notificationservice.service.SmsService {

    @Autowired
    private TwilioConfig twilioConfig;

    public void SendMessage(MessageDTO messageDTO){
        PhoneNumber to = new PhoneNumber(messageDTO.getNumber()); //set receiver phone number
        PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());//set sender phone number

        String messageBody = messageDTO.getMessageBody(); //set message body
        Message message = Message
                .creator(to, from,
                        messageBody)
                .create();  //send the message
    }
}
