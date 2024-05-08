package com.skillsprint.courseservice.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dvmgc2jjh",
                "api_key", "786541346192137",
                "api_secret", "CHyhqvQwsoVHILTEaWoJ2_SKa4c"
        ));
    }
}
