package com.thesis.alumni.system.controller;

import com.thesis.alumni.system.dto.BaseResponse;
import com.thesis.alumni.system.entity.Article;
import com.thesis.alumni.system.entity.Category;
import com.thesis.alumni.system.service.ArticleService;
import com.thesis.alumni.system.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    public final CategoryService categoryService;
    public final ArticleService articleService;

    @GetMapping
    public ResponseEntity<?> getCategories() {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message("OK")
                        .status(200)
                        .data(categories)
                        .timestamp(new Date())
                        .build(), HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<?> getArticles(
            @PathVariable() String slug,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "25") Integer limit
    ) {
        Page<Article> articles = articleService.findArticlesByCategory(slug, page, limit);
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message("OK")
                        .status(200)
                        .data(articles)
                        .timestamp(new Date())
                .build(), HttpStatus.OK);
    }
}
