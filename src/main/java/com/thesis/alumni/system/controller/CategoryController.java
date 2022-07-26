package com.thesis.alumni.system.controller;

import com.thesis.alumni.system.dto.BaseResponse;
import com.thesis.alumni.system.entity.Category;
import com.thesis.alumni.system.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    public final CategoryService categoryService;

    @GetMapping("/{slug}")
    public ResponseEntity<?> getArticles(@PathVariable String slug) throws Exception {
        Category category = categoryService.findCategory(slug);
        if (category == null)
            throw new Exception("Không tồn tại danh mục này");
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message("OK")
                        .status(200)
                        .data(category.getArticles())
                        .timestamp(new Date())
                .build(), HttpStatus.OK);
    }
}
