package com.thesis.alumni.system.controller;



import com.thesis.alumni.system.model.Article;
import com.thesis.alumni.system.model.User;
import com.thesis.alumni.system.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/articles")
@AllArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public List<Article> findAll(){
        return articleService.findAll();
    };

    @GetMapping("/{slug}")
    public Article getArticle(@PathVariable String slug) {
        return articleService.findBySlug(slug);
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }


}
