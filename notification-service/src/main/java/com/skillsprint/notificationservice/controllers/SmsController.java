package com.skillsprint.notificationservice.controllers;
import com.skillsprint.notificationservice.DTO.MessageDTO;
import com.skillsprint.notificationservice.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/messages/")
public class SmsController {

    @Autowired
    private final SmsService smsService; // Assuming you have a TwilioService class that contains the SendMessage method

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }


    @PostMapping("/sendSms")
    public void sendSms(@RequestBody MessageDTO messageDTO) {
        smsService.SendMessage(messageDTO);
    }
}