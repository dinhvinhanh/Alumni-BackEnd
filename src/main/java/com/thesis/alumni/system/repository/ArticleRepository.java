package com.thesis.alumni.system.repository;


import com.thesis.alumni.system.entity.Article;
import com.thesis.alumni.system.enums.ArticleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findArticleBySlug(String slug);
    Article findArticleBySlugAndStatusIs(String slug, ArticleType status);
    Page<Article> findArticlesByTitleContainsIgnoreCaseAndStatusIs(String title, ArticleType status, Pageable pageable);
    Page<Article> findArticlesByStatusIn(List<ArticleType> status, Pageable pageable);
    Page<Article> findArticlesByStatusInAndUser_Email(List<ArticleType> status, String email, Pageable pageable);
    Page<Article> findArticlesByCategorySlugAndStatusIs(String slug, ArticleType status, Pageable pageable);
}