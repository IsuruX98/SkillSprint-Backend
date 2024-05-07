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
    private String contactNo;
    protected String password;
    protected String userType;
    protected LocalDateTime updatedAt;


    public String getUser_Name() {
        return userName;
    }
}
