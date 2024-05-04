package com.skillsprint.courseservice.service;

import com.skillsprint.courseservice.dto.ModuleDTO;

import java.util.List;

public interface ModuleService {
    Object addModule(ModuleDTO moduleDTO);
    List<ModuleDTO> getModulesByCourseId(String courseId);
}
