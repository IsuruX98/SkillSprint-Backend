package com.skillsprint.courseservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
public class Video extends CommonEntity {

    @Id
    private ObjectId id;
    private String title;
    private String duration;
    private String url;
}
