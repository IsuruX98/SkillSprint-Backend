package com.skillsprint.courseservice.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
@Data
@EqualsAndHashCode(callSuper = true)
public class Quiz extends CommonEntity {

    @Id
    private ObjectId id;
}
