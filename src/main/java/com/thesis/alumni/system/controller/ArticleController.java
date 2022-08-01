package com.thesis.alumni.system.controller;



import com.thesis.alumni.system.dto.ArticleDto;
import com.thesis.alumni.system.dto.BaseResponse;
import com.thesis.alumni.system.entity.Article;
import com.thesis.alumni.system.enums.ArticleType;
import com.thesis.alumni.system.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;
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
    public Page<Article> find(
            @RequestParam(defaultValue = "APPROVED", name = "status") ArticleType[] articleType,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "25") Integer limnit
    ) throws AccessDeniedException {
        if (Arrays.stream(articleType).anyMatch(type -> type.equals(ArticleType.HIDDEN) || type.equals(ArticleType.PENDING))) {
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            if (authorities.contains("ROLE_ANONYMOUS"))
                throw new AccessDeniedException("Bạn không có quyền");
        }
        return articleService.findArticlesByStatus(articleType, page, limnit);
    };

    @GetMapping("/search")
    public ResponseEntity<?> searchArticle(
            @RequestParam(name = "q") String text,
            @RequestParam(defaultValue = "20") Integer limit,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        Page<Article> articles = articleService.findArticlesByTitle(text, page, limit);
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
    public ResponseEntity<?> getArticle(@PathVariable String slug) throws Exception {
        Article article = articleService.findBySlug(slug);
        if (article == null)
            throw new Exception("Không tồn tại bài viết này");
        return new ResponseEntity(
                BaseResponse
                    .builder()
                    .message("OK")
                    .status(200)
                    .data(article)
                    .timestamp(new Date())
                .build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody ArticleDto article) {
        Article result = articleService.saveArticle(article);
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message("OK")
                        .status(200)
                        .data(result)
                        .timestamp(new Date())
                        .build(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(
                BaseResponse
                        .builder()
                        .message("OK")
                        .status(200)
                        .data(null)
                        .timestamp(new Date())
                        .build(), HttpStatus.OK);
    }
}
