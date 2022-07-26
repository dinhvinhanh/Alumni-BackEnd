package com.thesis.alumni.system.repository;

import com.thesis.alumni.system.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryBySlug(String slug);
}
