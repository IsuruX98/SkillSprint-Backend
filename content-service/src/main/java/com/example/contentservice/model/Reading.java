package com.example.contentservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collation = "readings")
public class Reading extends CommonEntity{

    @Id
    private ObjectId id;
    private String title;
    private String description;
    private String duration;
    private String moduleId;
}
