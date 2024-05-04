package com.example.contentservice.repository;

import com.example.contentservice.model.Reading;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReadingRepository extends MongoRepository<Reading, String> {

    Optional<Reading> findById(String id);

    List<Reading> findAllByModuleId(String moduleId);


}
