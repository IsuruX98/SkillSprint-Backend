package com.skillsprint.courseservice.repository;

import com.skillsprint.courseservice.model.Module;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends MongoRepository<Module, String> {

    Module findByModuleCode(String moduleCode);

    List<Module> findAllByCourseId(String courseId);
}
