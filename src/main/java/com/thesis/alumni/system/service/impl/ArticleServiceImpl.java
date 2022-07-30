package com.thesis.alumni.system.service.impl;

import com.thesis.alumni.system.entity.Article;
import com.thesis.alumni.system.enums.ArticleType;
import com.thesis.alumni.system.repository.ArticleRepository;
import com.thesis.alumni.system.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public List<Article> findArticlesByTitle(String title, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("updatedAt").descending());
        return articleRepository.findArticlesByTitleContainsIgnoreCase(title, pageable);
    }

    @Override
    public List<Article> findArticlesByStatus(ArticleType status, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("updatedAt").descending());
        return articleRepository.findArticlesByStatusIs(status, pageable);
    }
}
