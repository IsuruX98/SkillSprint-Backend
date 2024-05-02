package com.example.contentservice.service;

import com.example.contentservice.model.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideoService {

    Object uploadVideo(MultipartFile videoFile, String title, String moduleId) throws IOException;
}
