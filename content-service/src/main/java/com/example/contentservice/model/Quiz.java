package com.example.contentservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "quizes")
public class Quiz extends CommonEntity {
    @Id
    private ObjectId id;
    private String moduleId;
}
