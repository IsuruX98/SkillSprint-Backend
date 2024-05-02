package com.example.contentservice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CommonEntity {

    private String createdUser;
    private String lastUpdatedUser;

}
