package com.skillsprint.userservice.service.ServiceInterfaces;

import com.skillsprint.userservice.DTO.AuthDTO;
import com.skillsprint.userservice.DTO.JWTResponse;
import com.skillsprint.userservice.DTO.ResponseDTO;
import com.skillsprint.userservice.DTO.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface AuthService {

    JWTResponse signin(UserDTO request);
    JWTResponse login(AuthDTO request);

    void validateTokenAndGetUser(String token);

    void logout(HttpServletRequest request);
}
