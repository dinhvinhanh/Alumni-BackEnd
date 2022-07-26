package com.thesis.alumni.system.repository;


import com.thesis.alumni.system.model.Article;
import com.thesis.alumni.system.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findArticleBySlug(String slug);
    List<Article> findArticlesByTitleContainsIgnoreCase(String title, Pageable pageable);
}