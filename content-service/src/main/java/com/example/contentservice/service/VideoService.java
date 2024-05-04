package com.example.contentservice.service;

import com.example.contentservice.dto.VideoDTO;
import com.example.contentservice.model.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VideoService {

    Object uploadVideo(MultipartFile videoFile, String title, String moduleId) throws IOException;

     List<VideoDTO> getVedioListbyModuleId(String moduleId);
}
