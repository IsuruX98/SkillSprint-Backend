package com.example.contentservice.repository;

import com.example.contentservice.model.Quiz;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepo extends MongoRepository<Quiz, String> {
    List<Quiz> findByModuleId(String moduleId);

    void deleteByModuleId(String moduleId);
}
