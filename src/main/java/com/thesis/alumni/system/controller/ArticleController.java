package com.thesis.alumni.system.controller;



import com.thesis.alumni.system.dto.BaseResponse;
import com.thesis.alumni.system.model.Article;
import com.thesis.alumni.system.model.User;
import com.thesis.alumni.system.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
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

    @GetMapping("/search")
    public ResponseEntity<?> searchArticle(
            @RequestParam(name = "q") String text,
            @RequestParam(defaultValue = "20") Integer limit,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        List<Article> articles = articleService.findArticlesByTitle(text, page, limit);
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message("OK")
                        .status(200)
                        .data(articles)
                        .timestamp(new Date())
                        .build(), HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    public ResponseEntity getArticle(@PathVariable String slug) throws Exception {
        Article article = articleService.findBySlug(slug);
        if (article == null)
            throw new Exception("Không tồn tại bài viết này");
        return new ResponseEntity<>(
                BaseResponse
                    .builder()
                    .message("OK")
                    .status(200)
                    .data(article)
                    .timestamp(new Date())
                .build(), HttpStatus.OK);
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }


}
