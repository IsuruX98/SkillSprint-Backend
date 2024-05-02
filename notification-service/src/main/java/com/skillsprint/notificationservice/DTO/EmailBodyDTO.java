package com.skillsprint.notificationservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailBodyDTO {
    private String to;
    private String subject;
    private String msg;
}
