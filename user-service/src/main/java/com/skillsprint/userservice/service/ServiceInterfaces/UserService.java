package com.skillsprint.userservice.service.ServiceInterfaces;

import com.skillsprint.userservice.DTO.ResponseDTO;
import com.skillsprint.userservice.DTO.UserDTO;
import com.skillsprint.userservice.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    UserDTO getUserById(String userId);
    List<UserDTO> getAllUsers();
    UserDTO getUserByEmail(String email);
    void deleteUserById(String userId);
    UserDTO updateUserById(String userId, UserDTO updatedUserDTO);

    UserDetailsService userDetailsService();
}
