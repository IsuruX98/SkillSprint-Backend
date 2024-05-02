package com.skillsprint.userservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    protected String userId;
    protected String userName;
    protected String email;
    protected String password;
    protected String userType;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}
