package com.skillsprint.courseservice.controllers;

import com.skillsprint.courseservice.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/course-controller")
@AllArgsConstructor
@NoArgsConstructor
public class CategoryController {

    @Autowired
    CategoryService categoryService;
}
