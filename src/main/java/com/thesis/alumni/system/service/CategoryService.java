package com.thesis.alumni.system.service;

import com.thesis.alumni.system.model.Category;

public interface CategoryService {
    Category findCategory(String slug);
}
