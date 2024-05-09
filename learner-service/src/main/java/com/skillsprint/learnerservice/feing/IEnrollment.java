package com.skillsprint.learnerservice.feing;
import com.skillsprint.learnerservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("USER-SERVICE")
public interface IEnrollment {

    @GetMapping("api/v1/users")
    UserDTO getUserByEmail(@RequestParam("email") String email, @RequestHeader("userRole") String userRole);
}
