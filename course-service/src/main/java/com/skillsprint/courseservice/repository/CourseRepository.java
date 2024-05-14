package com.skillsprint.courseservice.repository;

import com.skillsprint.courseservice.model.Course;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {

    Course findCourseById(String courseCode);

    List<Course> findAllByCategoryId(String categoryId);

    List<Course> findAllByStatus(String status);

    Course findByIdAndStatus(String id,String status);

    List<Course> findAllByInstructorId(String instructorId);
}
