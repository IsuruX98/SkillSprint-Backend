package com.example.contentservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "videos")
@Data
public class Video extends CommonEntity {

    @Id
    private String id;
    private String title;
    private String duration;
    private String url;
    private String moduleId;
}
