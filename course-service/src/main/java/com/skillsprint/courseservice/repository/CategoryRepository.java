package com.skillsprint.courseservice.repository;

import com.skillsprint.courseservice.model.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, ObjectId> {

    Category findByCategoryCode(String categoryCode);
}
