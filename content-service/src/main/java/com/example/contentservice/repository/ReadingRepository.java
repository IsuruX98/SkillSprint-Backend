package com.example.contentservice.repository;

import com.example.contentservice.model.Reading;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingRepository extends MongoRepository<Reading, ObjectId> {

    Reading findByIdAndStatus(ObjectId id, String status);

    List<Reading> findAllByModuleId(String moduleId);


}
