package com.example.contentservice.repository;

import com.example.contentservice.model.Video;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends MongoRepository<Video, ObjectId> {

}
