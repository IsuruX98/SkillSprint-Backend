package com.skillsprint.courseservice.service.impl;

import com.skillsprint.courseservice.dto.ModuleDTO;
import com.skillsprint.courseservice.model.Module;
import com.skillsprint.courseservice.repository.ModuleRepository;
import com.skillsprint.courseservice.service.ModuleService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    ModelMapper mapper = new ModelMapper();

    @Override
    public Object addModule(ModuleDTO moduleDTO) {
        try{

            Module module = moduleRepository.findByModuleCode(moduleDTO.getModuleCode());

            if(module == null) {
                moduleRepository.save(mapper.map(moduleDTO, Module.class));
                return ResponseEntity.status(HttpStatus.OK).body("Module Added successfully.");
            }else
                return ResponseEntity.status(HttpStatus.OK).body("Module Already available.");

        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public List<ModuleDTO> getModulesByCourseId(String courseId) {
        try{
            List<Module> moduleList  = moduleRepository.findAllByCourseId(courseId);

            if(!moduleList.isEmpty()){
                List<ModuleDTO> moduleDTOList = new ArrayList<>();
                moduleList.forEach(module -> moduleDTOList.add(mapper.map(module, ModuleDTO.class)));
                return moduleDTOList;
            }

        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
        return null;
    }


}
