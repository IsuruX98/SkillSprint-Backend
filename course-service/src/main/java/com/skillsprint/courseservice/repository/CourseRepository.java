package com.skillsprint.courseservice.repository;

import com.skillsprint.courseservice.model.Course;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<Course, ObjectId> {

    Course findCourseByCourseCodeAndStatus(String courseCode, String status);
}
