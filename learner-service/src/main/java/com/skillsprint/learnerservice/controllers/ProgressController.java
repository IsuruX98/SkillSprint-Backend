package com.skillsprint.learnerservice.controllers;

import com.skillsprint.learnerservice.DTO.ProgressDTO;
import com.skillsprint.learnerservice.Service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/progress") // Endpoint for progress
public class ProgressController {

    private final ProgressService progressService; // Service dependency injection

    @Autowired
    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping
    public ResponseEntity<ProgressDTO> createProgress(@RequestBody ProgressDTO progressDTO) {
        ProgressDTO createdProgress = progressService.createProgress(progressDTO);
        return new ResponseEntity<>(createdProgress, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgressDTO> getProgressById(@PathVariable String id) {
        ProgressDTO progressDTO = progressService.getProgressById(id);
        return new ResponseEntity<>(progressDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}/{indexModule}")
    public ResponseEntity<ProgressDTO> updateProgress(@PathVariable String id,@PathVariable int indexModule) {
        ProgressDTO updatedProgress = progressService.updateProgress(id,indexModule);
        return new ResponseEntity<>(updatedProgress, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgressById(@PathVariable String id) {
        progressService.deleteProgressById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ProgressDTO>> getAllProgresses() {
        List<ProgressDTO> progressDTOs = progressService.getAllProgresses();
        return new ResponseEntity<>(progressDTOs, HttpStatus.OK);
    }
}

