package com.skillsprint.learnerservice.repository;

import com.skillsprint.learnerservice.model.Progress;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgressRepository extends MongoRepository<Progress, String> {
    Optional<Object> findByUserIdAndCourseId(String userId, String courseId);
}
