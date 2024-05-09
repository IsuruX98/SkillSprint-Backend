package com.skillsprint.userservice.service;

import com.skillsprint.userservice.DTO.UserDTO;
import com.skillsprint.userservice.model.User;
import com.skillsprint.userservice.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional
@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    ModelMapper modelMapper;
    public User saveUser(User user) {
        user.setUpdatedAt(LocalDateTime.now());
        return userRepo.save(user);
    }

    public UserDTO getUserById(String userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        user.setUserName(user.getUser_Name());
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO getUserByEmail(String email) {
        User user = userRepo.findUserByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
        return modelMapper.map(user,UserDTO.class);
    }

    public void deleteUserById(String userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        userRepo.delete(user);
    }



    public UserDTO updateUserById(String userId, UserDTO updatedUserDTO) {
        UserDTO existingUserDTO = getUserById(userId);
        if (existingUserDTO == null) {
            return null;
        } else {
            User user = modelMapper.map(existingUserDTO, User.class);

            // Update attributes only if they are provided by the user
            if (updatedUserDTO.getUserName() != null &&  !updatedUserDTO.getUserName().isEmpty() ) {
                user.setUserName(updatedUserDTO.getUserName());
            }
            if (updatedUserDTO.getEmail() != null &&  !updatedUserDTO.getEmail().isEmpty()) {
                user.setEmail(updatedUserDTO.getEmail());
            }
            if (updatedUserDTO.getContactNo() != null &&  !updatedUserDTO.getContactNo().isEmpty()) {
                user.setContactNo(updatedUserDTO.getContactNo());
            }
            if (updatedUserDTO.getPassword() != null &&  !updatedUserDTO.getPassword().isEmpty()) {
                user.setPassword(updatedUserDTO.getPassword());
            }
            if (updatedUserDTO.getUserType() != null &&  !updatedUserDTO.getUserType().isEmpty()) {
                user.setUserType(updatedUserDTO.getUserType());
            }

            user.setUpdatedAt(LocalDateTime.now());
            user.setUserId(userId);

            // Save the updated user
            userRepo.save(user);
            user.setUserName(user.getUser_Name());

            return modelMapper.map(user, UserDTO.class);
        }
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                if (userRepo.existsUserByEmail(username)) {
                    return (UserDetails) userRepo.findUserByEmail(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
                }
                else {
                    return (UserDetails) userRepo.findUserByEmail(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

                }
            }
        };
    }

}

