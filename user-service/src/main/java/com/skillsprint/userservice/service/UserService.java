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
        User user = userRepo.findById(userId).orElse(null);
            return modelMapper.map(user, UserDTO.class);
        }


    public void deleteUserById(String userId) {
        User user = userRepo.findById(userId).orElse(null);
        userRepo.delete(user);
        return;
        }


    public UserDTO updateUserById(String userId, UserDTO updatedUserDTO) {
        UserDTO existingUserDTO = getUserById(userId);
        if (existingUserDTO == null) {
            return null;
        }
        else  {
            User user = modelMapper.map(updatedUserDTO, User.class);
            user.setUpdatedAt(LocalDateTime.now());
            user.setUserId(userId);
            userRepo.save(user);
            return modelMapper.map(user, UserDTO.class);
        }
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                if (userRepo.existsUserByEmail(username)) {
                    return (UserDetails) userRepo.findUserByEmail(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                }
                else {
                    return (UserDetails) userRepo.findUserByEmail(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

                }
            }
        };
    }

}

