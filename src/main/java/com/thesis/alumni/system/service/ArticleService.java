package com.thesis.alumni.system.service;

import com.thesis.alumni.system.dto.ArticleDto;
import com.thesis.alumni.system.entity.Article;
import com.thesis.alumni.system.enums.ArticleType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService{
    List<Article> findAll();
    Article findBySlug(String slug);

    Article saveArticle(ArticleDto article);
    Page<Article> findArticlesByTitle(String title, Integer page, Integer limit);
    Page<Article> findArticlesByStatus(ArticleType[] status, Integer page, Integer limit);
    Page<Article> findArticlesByCategory(String slug, Integer page, Integer limit);
    void deleteArticle(Long article);
}