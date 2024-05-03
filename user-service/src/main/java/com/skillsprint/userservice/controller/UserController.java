package com.skillsprint.userservice.controller;

import com.skillsprint.userservice.DTO.UserDTO;
import com.skillsprint.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/users")
@RestController
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String userId,@RequestHeader("userRole") String userRole) {
        System.out.println("menn"+userRole);
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable String userId, @RequestBody UserDTO updatedUserDTO) {

        UserDTO updatedUser = userService.updateUserById(userId, updatedUserDTO);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
