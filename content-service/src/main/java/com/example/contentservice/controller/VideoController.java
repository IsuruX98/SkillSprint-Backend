package com.example.contentservice.controller;

import com.example.contentservice.dto.ReadingDTO;
import com.example.contentservice.dto.VideoDTO;
import com.example.contentservice.model.Video;
import com.example.contentservice.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/v1/content-controller/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/")
    public ResponseEntity<Object> uploadVideo(@RequestPart("video") MultipartFile videoFile,
                                              @RequestPart("title") String title,
                                              @RequestPart("moduleId") String moduleId) {
        try {
            Object result = videoService.uploadVideo(videoFile, title, moduleId);
            if (result.equals("Video Uploaded Successfully.")) {
                return ResponseEntity.status(HttpStatus.OK).body(result);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Video upload Failed.");
        }
    }

    @GetMapping("/{moduleId}")
    public List<VideoDTO> getAllVideos(@PathVariable String moduleId){
            return videoService.getVedioListbyModuleId(moduleId);
    }



}

