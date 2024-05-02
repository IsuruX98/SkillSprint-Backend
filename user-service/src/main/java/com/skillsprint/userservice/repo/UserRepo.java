package com.skillsprint.userservice.repo;

import com.skillsprint.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User,String> {
    Optional<User> findUserByEmail(String email);
    Boolean existsUserByEmail(String email);
}
