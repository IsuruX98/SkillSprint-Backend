package com.skillsprint.courseservice.service;

import com.skillsprint.courseservice.dto.CategoryDTO;

public interface CategoryService {

    Object addCategory(CategoryDTO categoryDTO);

    Object getCategoryByCategoryCode(String categoryCode);

    Object editCategory(CategoryDTO categoryDTO);

    Object deleteCategoryByCategoryCode(String categoryCode);
}
