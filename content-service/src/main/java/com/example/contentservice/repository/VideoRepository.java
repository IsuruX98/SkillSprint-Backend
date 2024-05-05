package com.example.contentservice.repository;

import com.example.contentservice.model.Reading;
import com.example.contentservice.model.Video;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends MongoRepository<Video, ObjectId> {
    List<Video> findAllByModuleId(String moduleId);
}
