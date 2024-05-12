package com.skillsprint.courseservice.controllers;

import com.skillsprint.courseservice.dto.ModuleDTO;
import com.skillsprint.courseservice.service.ModuleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/module-controller")
@AllArgsConstructor
public class ModuleController {

    //TODO - haven't tested yet.

    private final ModuleService moduleService;

    @PostMapping("")
    //@PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Object> addModule(@RequestBody ModuleDTO moduleDTO){
        return ResponseEntity.status(HttpStatus.OK).body(moduleService.addModule(moduleDTO));
    }


    @GetMapping("/{courseId}")
    // @PreAuthorize("hasAnyAuthority('admin:read', 'faculty:read', 'student:read')")
    public ResponseEntity<List<ModuleDTO>> getModulesByCourseId(@PathVariable String courseId){
        return ResponseEntity.status(HttpStatus.OK).body(moduleService.getModulesByCourseId(courseId));
    }


    //TODO - not tested below methods

    @PutMapping("")
    public ResponseEntity<String> updateModule(@RequestBody ModuleDTO moduleDTO){

        String response = moduleService.updateModule(moduleDTO);

        if(Objects.equals(response, "Module Updated Successfully"))
            return ResponseEntity.ok(response);
        else if(Objects.equals(response, "Module not found"))
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("{moduleId}")
    public ResponseEntity<String> deleteModuleById(@PathVariable String moduleId){

        String response = moduleService.deleteModule(moduleId);

        if(Objects.equals(response, "Module Deleted Successfully"))
            return ResponseEntity.ok(response);
        else if(Objects.equals(response, "Module not found"))
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.internalServerError().build();
    }
}
