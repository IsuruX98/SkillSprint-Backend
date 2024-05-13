package com.skillsprint.notificationservice.controllers;
import com.skillsprint.notificationservice.DTO.MessageDTO;
import com.skillsprint.notificationservice.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@RestController
@RequestMapping("api/v1/messages/")
public class SmsController {

    @Autowired
    private final SmsService smsService;

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }


  @PostMapping("/sendSms")
    public void sendSms(@RequestBody MessageDTO messageDTO) {
        smsService.SendMessage(messageDTO);  //call sendMessage service method
    }
/*
Method for notify.lk api
    @PostMapping("/sendSms")
    public void sendSms(@RequestBody MessageDTO messageDTO) throws UnsupportedEncodingException {
        // Construct the URL with parameters
        String url = "https://app.notify.lk/api/v1/send";
        String userId = "27134";
        String apiKey = "JXCbQV9hxiMHkcrI0mYX";
        String senderId = "NotifyDEMO";
        String phoneNumber = messageDTO.getNumber();
        String message = messageDTO.getMessageBody();

        try {
            message = URLDecoder.decode(message, "UTF-8");
            // Manually replace line breaks and spaces
            message = message.replace("\n", " ");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Error decoding message: " + e.getMessage());
        }
        // Append query parameters to the URL
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("user_id", userId)
                .queryParam("api_key", apiKey)
                .queryParam("sender_id", senderId)
                .queryParam("to", phoneNumber)
                .queryParam("message", message);

        // Create RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Send POST request and handle the response
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.POST,
                null,
                String.class);

        // You can handle the response status if needed
        HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            System.out.println("Message sent successfully!");
        } else {
            System.err.println("Failed to send message. Status code: " + statusCode);
        }
    }
*/
}
