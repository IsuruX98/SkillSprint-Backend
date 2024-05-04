package com.example.contentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTO {
    private ObjectId id;
    private String title;
    private String duration;
    private String url;
    private String moduleId;
}
