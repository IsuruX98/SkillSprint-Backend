package com.skillsprint.notificationservice.controllers;

import com.skillsprint.notificationservice.DTO.EmailBodyDTO;
import com.skillsprint.notificationservice.service.EmailSendService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/email")
@AllArgsConstructor
@NoArgsConstructor
public class EmailCtrl {
    private  EmailSendService emailSendService;
    @PostMapping("/send-email")
    public ResponseEntity sendEmail(@RequestBody EmailBodyDTO dto){
        emailSendService.sendEmail(dto.getTo(), dto.getSubject(), dto.getMsg());
        return ResponseEntity.ok("Sent Email");
    }
}
