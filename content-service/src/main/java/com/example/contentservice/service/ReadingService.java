package com.example.contentservice.service;

import com.example.contentservice.dto.ReadingDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface ReadingService {
    Object addReading(ReadingDTO readingDTO);

    ReadingDTO getReadingById(ObjectId readingId);

    List<ReadingDTO> findAllByModuleId(String moduleId);

    Object updateReading(ReadingDTO readingDTO);
}
