package com.skillsprint.courseservice.repository;

import com.skillsprint.courseservice.model.Enrollment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends MongoRepository<Enrollment, ObjectId> {


}
