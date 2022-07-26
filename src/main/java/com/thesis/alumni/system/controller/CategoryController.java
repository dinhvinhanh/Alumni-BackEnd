package com.thesis.alumni.system.controller;

import com.thesis.alumni.system.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    public final CategoryService categoryService;

    @GetMapping("/{slug}")
    public Collection<?> getArticles(@PathVariable String slug) {
        return categoryService.findCategory(slug).getArticles();
    }
}
