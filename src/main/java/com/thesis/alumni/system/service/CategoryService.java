package com.thesis.alumni.system.service;

import com.thesis.alumni.system.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    Category findCategory(String slug);

    List<Category> findAll();
}
