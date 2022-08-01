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
    Page<Article> findArticlesByTitleContainsIgnoreCase(String title, Pageable pageable);
    Page<Article> findArticlesByStatusIn(List<ArticleType> status, Pageable pageable);
    Page<Article> findArticlesByCategorySlug(String slug, Pageable pageable);
}