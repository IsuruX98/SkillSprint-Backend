package com.skillsprint.userservice.service;

import com.skillsprint.userservice.DTO.AuthDTO;
import com.skillsprint.userservice.DTO.JWTResponse;
import com.skillsprint.userservice.DTO.UserDTO;
import com.skillsprint.userservice.model.User;
import com.skillsprint.userservice.repo.UserRepo;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static javax.crypto.Cipher.SECRET_KEY;

@Transactional
@Service
public class AuthService implements com.skillsprint.userservice.service.ServiceInterfaces.AuthService {


    private JWTResponse jwtResponse;
    @Autowired
    private  JWTService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JWTResponse signin(UserDTO request){

        if (userRepo.existsUserByEmail(request.getEmail())) {      //check  user exist by that email
            throw new IllegalArgumentException("User with this email already exists.");
        }
        User user=new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setContactNo(request.getContactNo());
        if (request.getUserType() == null) {
            user.setUserType("student");
        } else {
            user.setUserType(request.getUserType());
        }
        user.setUserName(request.getUserName());


        var user_= userService.saveUser(user);  //call user service save user method
        String jwt =jwtService.generateToken(user_);  //generate token for registered user

        return JWTResponse
                .builder().token(jwt)
                .user(user_)
                .build();
    }


    public JWTResponse login(AuthDTO request) {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException ex) {
            throw new IllegalArgumentException("Invalid email or password!");
        }

        if(userRepo.existsUserByEmail(request.getEmail())){  //check  weather user exist
            var user = userRepo.findUserByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("User not found!"));

            var jwt = jwtService.generateToken(user); // generate token for user
            JWTResponse.builder().user(user);
            return JWTResponse
                    .builder().token(jwt)
                    .user(user)
                    .build();
        }
        return JWTResponse.builder().build();
    }
    public void validateTokenAndGetUser(String token) {    //validate token
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(String.valueOf(SECRET_KEY))
                    .parseClaimsJws(token)
                    .getBody();

            String userId = claims.getSubject();

        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException ex) {

        }
    }

    public void logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
    }  //logout

}