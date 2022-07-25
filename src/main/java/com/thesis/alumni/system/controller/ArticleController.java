package com.thesis.alumni.system.controller;



import com.thesis.alumni.system.model.Article;
import com.thesis.alumni.system.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/article")
    public List<Article> findAll(){
        return articleService.findAll();
    };

}
