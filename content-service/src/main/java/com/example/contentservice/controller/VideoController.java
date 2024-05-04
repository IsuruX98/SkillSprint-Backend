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
    public ResponseEntity<Object> uploadVideo(@RequestParam("video") MultipartFile videoFile,
                                             @RequestParam("title") String title,
                                             @RequestParam("moduleId") String moduleId
                                             ) throws IOException {

        return ResponseEntity.status(HttpStatus.OK).body(videoService.uploadVideo(videoFile, title, moduleId));

    }

    @GetMapping("/{moduleId}")
    public ResponseEntity<List<VideoDTO>> getAllVideos(@PathVariable String moduleId){
            return ResponseEntity.status(HttpStatus.OK).body(videoService.getVedioListbyModuleId(moduleId));
        }

    }

