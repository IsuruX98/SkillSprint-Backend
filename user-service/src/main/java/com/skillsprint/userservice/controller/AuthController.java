package com.skillsprint.userservice.controller;


import com.skillsprint.userservice.DTO.AuthDTO;
import com.skillsprint.userservice.DTO.JWTResponse;
import com.skillsprint.userservice.DTO.ResponseDTO;
import com.skillsprint.userservice.DTO.UserDTO;
import com.skillsprint.userservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("api/v1/auth")
@RestController
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    ResponseDTO responseDTO;
    JWTResponse jwtResponse;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO request) {
        jwtResponse = authService.signin(request);

        return ResponseEntity.ok()
                .body(new ResponseDTO( "Success", jwtResponse));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody AuthDTO request) {
        jwtResponse=authService.login(request);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("user", jwtResponse.getUser());
        response.put("token", jwtResponse.getToken());
        return ResponseEntity.ok()
                .body(new ResponseDTO( "Success", response));
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        authService.validateTokenAndGetUser(token);
        return "token is valid";
    }
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(new ResponseDTO("Error", response));
    }
}
