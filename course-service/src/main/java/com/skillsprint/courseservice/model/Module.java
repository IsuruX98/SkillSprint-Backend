package com.skillsprint.courseservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "modules")
public class Module {

    @Id
    private ObjectId id;
    private String moduleName;
    private String courseId;
   // private List<Video> videoList;
    //private List<Reading> readingList;
    //private List<Quiz> quizesList;
    //thumbnail
}
