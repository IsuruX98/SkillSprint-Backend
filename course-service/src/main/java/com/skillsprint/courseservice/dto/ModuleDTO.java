package com.skillsprint.courseservice.dto;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class ModuleDTO {

    private ObjectId id;
    private String moduleName;
    private String moduleCode;
    private String courseId;

}
