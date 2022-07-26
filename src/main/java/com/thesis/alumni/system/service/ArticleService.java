package com.thesis.alumni.system.service;

import com.thesis.alumni.system.entity.Article;

import java.util.List;

public interface ArticleService{
    List<Article> findAll();
    Article findBySlug(String slug);

    Article saveArticle(Article article);
    List<Article> findArticlesByTitle(String title, Integer page, Integer limit);
}