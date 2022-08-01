package com.thesis.alumni.system.service.impl;

import com.thesis.alumni.system.dto.ArticleDto;
import com.thesis.alumni.system.entity.Article;
import com.thesis.alumni.system.entity.Category;
import com.thesis.alumni.system.enums.ArticleType;
import com.thesis.alumni.system.repository.ArticleRepository;
import com.thesis.alumni.system.repository.CategoryRepository;
import com.thesis.alumni.system.service.ArticleService;
import com.thesis.alumni.system.utils.AuthUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    public final ModelMapper modelMapper;
    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article findBySlug(String slug) {
        return articleRepository.findArticleBySlug(slug);
    }

    @Override
    public Article saveArticle(ArticleDto article) {
        Article result = modelMapper.map(article, Article.class);
        boolean isAdmin = AuthUtil.isAdmin();
        if (isAdmin) {
            result.setStatus(ArticleType.APPROVED);
        } else {
            result.setStatus(ArticleType.PENDING);
        }
        Category category = categoryRepository.findById(article.getCategoryId()).orElse(null);
        result.setCategory(category);
        result.setId(null);
        return articleRepository.save(result);
    }

    @Override
    public Page<Article> findArticlesByTitle(String title, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("updateAt").descending());
        return articleRepository.findArticlesByTitleContainsIgnoreCase(title, pageable);
    }

    @Override
    public Page<Article> findArticlesByStatus(ArticleType[] status, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("updateAt").descending());
        Page<Article> result = articleRepository.findArticlesByStatusIn(Arrays.stream(status).collect(Collectors.toList()), pageable);
        return result;
    }

    @Override
    public Page<Article> findArticlesByCategory(String slug, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("updateAt").descending());
        Page<Article> result = articleRepository.findArticlesByCategorySlug(slug, pageable);
        return result;
    }

    @Override
    public void deleteArticle(Long articleId) {
        Article article = new Article();
        article.setId(articleId);
        articleRepository.delete(article);
    }
}
