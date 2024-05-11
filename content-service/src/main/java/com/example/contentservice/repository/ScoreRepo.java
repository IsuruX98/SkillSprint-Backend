package com.example.contentservice.repository;

import com.example.contentservice.model.Score;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepo extends MongoRepository<Score, String> {
}
