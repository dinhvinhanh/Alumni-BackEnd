package com.thesis.alumni.system.service;

import com.thesis.alumni.system.entity.Category;

public interface CategoryService {
    Category findCategory(String slug);
}
