package com.thesis.alumni.system.service.impl;

import com.thesis.alumni.system.model.Article;
import com.thesis.alumni.system.repository.ArticleRepository;
import com.thesis.alumni.system.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private final ArticleRepository articleRepository;


    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }


}
