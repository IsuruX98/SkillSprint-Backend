package com.skillsprint.courseservice.controllers;

import com.skillsprint.courseservice.dto.CategoryDTO;
import com.skillsprint.courseservice.dto.CourseDTO;
import com.skillsprint.courseservice.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category-controller")
@AllArgsConstructor
@NoArgsConstructor
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //category APIs not tested.

    @PostMapping("")
    //@PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<Object> addCategory(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.addCategory(categoryDTO));
    }

    @GetMapping("/{categoryCode}")
    // @PreAuthorize("hasAnyAuthority('admin:read', 'faculty:read', 'student:read')")
    public ResponseEntity<Object> getCategoryByCategoryCode(@PathVariable String categoryCode) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategoryByCategoryCode(categoryCode));
    }

    @PutMapping("/")
    // @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Object> updateCourseByCourseCode(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.editCategory(categoryDTO));
    }


    @PutMapping("/delete/{categoryCode}")
    //   @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<Object> deleteCourseByCourseCode(@PathVariable String categoryCode){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteCategoryByCategoryCode(categoryCode));
    }

}
