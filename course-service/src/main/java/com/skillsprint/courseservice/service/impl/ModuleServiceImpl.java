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
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    ModelMapper mapper = new ModelMapper();

    @Override
    public String addModule(ModuleDTO moduleDTO) {
        try{

            Module module = moduleRepository.findByModuleCode(moduleDTO.getModuleCode());

            if(module == null) {
                moduleRepository.save(mapper.map(moduleDTO, Module.class));
                return "Module Added successfully.";
            }else
                return "Module Already available.";

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
            else
                log.error("No modules available for this course");


        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public String updateModule(ModuleDTO moduleDTO) {
        try{
            Optional<Module> module = moduleRepository.findById(moduleDTO.getId());

            if(module.isPresent()){
                Module module1 = module.get();
                if(moduleDTO.getModuleName() != null)
                    module1.setModuleName(moduleDTO.getModuleName());
                if(moduleDTO.getModuleCode() != null)
                    module1.setModuleCode(moduleDTO.getModuleCode());
                if(moduleDTO.getCourseId() != null)
                    module1.setCourseId(moduleDTO.getCourseId());

                moduleRepository.save(module1);
                return "Module Updated Successfully";
            }else
                return "Module not found";

        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public String deleteModule(String moduleId) {
        try{
            Optional<Module> module = moduleRepository.findById(moduleId);

            if(module.isPresent()){
                moduleRepository.deleteById(moduleId);
                return "Module Deleted Successfully";
            }else
                return "Module not found";

        }catch(Exception e){
            log.error(e.getMessage());
            throw e;
        }
    }


}
