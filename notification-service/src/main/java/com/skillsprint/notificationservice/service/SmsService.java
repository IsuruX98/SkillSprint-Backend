package com.skillsprint.notificationservice.service;

import com.skillsprint.notificationservice.DTO.MessageDTO;
import com.skillsprint.notificationservice.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Autowired
    private TwilioConfig twilioConfig;

    public void SendMessage(MessageDTO messageDTO){
        PhoneNumber to = new PhoneNumber(messageDTO.getNumber());
        PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());

        String messageBody = messageDTO.getMessageBody();
        Message message = Message
                .creator(to, from,
                        messageBody)
                .create();
    }
}
