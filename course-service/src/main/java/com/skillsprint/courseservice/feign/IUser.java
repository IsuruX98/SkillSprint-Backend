package com.skillsprint.courseservice.feign;
import com.skillsprint.courseservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("USER-SERVICE")
public interface IUser {

    @GetMapping("api/v1/users")
    ResponseEntity<String> getUserByEmail(@RequestParam("email") String email, @RequestHeader("userRole") String userRole);

    @GetMapping("api/v1/users")
    UserDTO getUserByEmail(@RequestParam("email") String email);

    @GetMapping("api/v1/users/getUser/{userId}")
    UserDTO getUserDTOById(@PathVariable String userId);
}
