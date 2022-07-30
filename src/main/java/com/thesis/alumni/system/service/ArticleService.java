package com.thesis.alumni.system.service;

import com.thesis.alumni.system.entity.Article;
import com.thesis.alumni.system.enums.ArticleType;

import java.util.List;

public interface ArticleService{
    List<Article> findAll();
    Article findBySlug(String slug);

    Article saveArticle(Article article);
    List<Article> findArticlesByTitle(String title, Integer page, Integer limit);
    List<Article> findArticlesByStatus(ArticleType status, Integer page, Integer limit);
}