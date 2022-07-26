package com.thesis.alumni.system.service.impl;

import com.thesis.alumni.system.model.Article;
import com.thesis.alumni.system.repository.ArticleRepository;
import com.thesis.alumni.system.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article findBySlug(String slug) {
        return articleRepository.findArticleBySlug(slug);
    }

    @Override
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }
}
