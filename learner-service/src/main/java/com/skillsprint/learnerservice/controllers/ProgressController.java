package com.skillsprint.learnerservice.controllers;

import com.skillsprint.learnerservice.DTO.ProgressDTO;
import com.skillsprint.learnerservice.Service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/progress")
public class ProgressController {

    private final ProgressService progressService;

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

    @GetMapping("/{userId}/{courseId}")  //  take the progress based on the course and user id
    public ResponseEntity<ProgressDTO> getProgressByUserIdAndCourseId(@PathVariable String userId, @PathVariable String courseId) {
        ProgressDTO progressDTO = progressService.getProgressByUserIdAndCourseId(userId, courseId);
        if (progressDTO != null) {
            return new ResponseEntity<>(progressDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{userId}/{courseId}/{indexModule}")
    public ResponseEntity<ProgressDTO> updateProgress(@PathVariable String userId,@PathVariable String courseId,@PathVariable int indexModule) {
        ProgressDTO updatedProgress = progressService.updateProgress(userId,courseId,indexModule);
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

