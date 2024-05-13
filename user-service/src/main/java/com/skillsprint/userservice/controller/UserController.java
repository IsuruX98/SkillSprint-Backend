package com.skillsprint.userservice.controller;

import com.skillsprint.userservice.DTO.ResponseDTO;
import com.skillsprint.userservice.DTO.UserDTO;
import com.skillsprint.userservice.service.ServiceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("api/v1/users")
@RestController
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String userId, @RequestHeader("userRole") String userRole) {
        if (!"student".equalsIgnoreCase(userRole)) {
            // If userRole is not "student", return a forbidden response
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        // If userRole is "student", proceed with getting the user by ID
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("getUser/{userId}")
    public UserDTO getUserDTOById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }
    @GetMapping
    public UserDTO getUserByEmail(@RequestParam("email") String email, @RequestHeader("userRole") String userRole) {

        return userService.getUserByEmail(email);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
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

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getlAllUser(){

         List<UserDTO> userDTOS= userService.getAllUsers();
         return ResponseEntity.ok(userDTOS);
        }

    //method for handle argument exception _ this return response when argument exception occur
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(new ResponseDTO("Error", response));
    }
}
