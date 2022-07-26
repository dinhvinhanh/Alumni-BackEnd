package com.thesis.alumni.system.service;

import com.thesis.alumni.system.model.Article;
import com.thesis.alumni.system.model.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ArticleService{
    List<Article> findAll();
    Article findBySlug(String slug);

    Article saveArticle(Article article);
    List<Article> findArticlesByTitle(String title, Integer page, Integer limit);
}