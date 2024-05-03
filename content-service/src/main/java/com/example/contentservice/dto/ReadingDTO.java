package com.example.contentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadingDTO {
    private ObjectId id;
    private String title;
    private String description;
    private String duration;
    private String moduleId;
}
