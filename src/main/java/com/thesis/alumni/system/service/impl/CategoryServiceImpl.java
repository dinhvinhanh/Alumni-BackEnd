package com.thesis.alumni.system.service.impl;

import com.thesis.alumni.system.model.Category;
import com.thesis.alumni.system.repository.CategoryRepository;
import com.thesis.alumni.system.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category findCategory(String slug) {
        return categoryRepository.findCategoryBySlug(slug);
    }
}
