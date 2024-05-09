package com.skillsprint.learnerservice.repository;

import com.skillsprint.learnerservice.model.Progress;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressRepository extends MongoRepository<Progress, String> {
}
