package com.skillsprint.notificationservice.controllers;

import com.skillsprint.notificationservice.DTO.EmailBodyDTO;
import com.skillsprint.notificationservice.service.EmailService;
import com.skillsprint.notificationservice.service.ServiceImpl.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/email") // Endpoint for mail sending
public class EmailCtrl {

    @Autowired
    private EmailService emailSendService; // Service dependency injection
    @PostMapping("/send-email")
    public ResponseEntity sendEmail(@RequestBody EmailBodyDTO dto){
        emailSendService.sendEmail(dto.getTo(), dto.getSubject(), dto.getMsg()); //call sendEMail method and set parameters from request body
        return ResponseEntity.ok("Sent Email");
    }
}
