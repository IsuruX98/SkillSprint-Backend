package com.skillsprint.notificationservice.service;
import com.skillsprint.notificationservice.DTO.MessageDTO;

public interface SmsService   {
    void SendMessage(MessageDTO messageDTO);
}
