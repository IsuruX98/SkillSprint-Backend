package com.skillsprint.courseservice.feign;
import com.skillsprint.courseservice.dto.EmailBodyDTO;
import com.skillsprint.courseservice.dto.MessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("NOTIFICATION-SERVICE")
public interface INotification {
    @PostMapping("/api/v1/email/send-email")
    public ResponseEntity sendEmail(@RequestBody EmailBodyDTO dto);


    @PostMapping("/api/v1/messages/sendSms")
    public void sendSms(@RequestBody MessageDTO messageDTO) ;
}