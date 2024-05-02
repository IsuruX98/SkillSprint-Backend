package com.example.contentservice.service.impl;

import com.cloudinary.Cloudinary;
import com.example.contentservice.model.Video;
import com.example.contentservice.repository.VideoRepository;
import com.example.contentservice.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j

public class VideoServiceImpl implements VideoService {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private VideoRepository videoRepository;

    @Transactional
    public Object uploadVideo(MultipartFile videoFile, String title, String moduleId) throws IOException {

        try{
            Map<String, String> uploadOptions = new HashMap<>();
            uploadOptions.put("resource_type","video");

            Map uploadResult = cloudinary.uploader().upload(videoFile.getBytes(), uploadOptions);

            String videoUrl = uploadResult.get("url").toString();

            Video video = new Video();
            video.setUrl(videoUrl);
            video.setTitle(title);
            video.setModuleId(moduleId);
            video.setDuration(uploadResult.get("duration").toString());

            videoRepository.save(video);

            return ResponseEntity.status(HttpStatus.OK).body("Video Uploaded Successfully.");

        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Video upload Failed.");
        }
    }
}
