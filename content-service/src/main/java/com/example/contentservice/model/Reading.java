package com.example.contentservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "readings")
public class Reading extends CommonEntity{

    @Id
    private String id;
    private String title;
    private String description;
    private String duration;
    private String moduleId;
    private String status;
}
