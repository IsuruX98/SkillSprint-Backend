package com.skillsprint.courseservice.controllers;

import com.skillsprint.courseservice.dto.ModuleDTO;
import com.skillsprint.courseservice.service.ModuleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
