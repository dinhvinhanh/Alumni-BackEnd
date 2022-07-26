package com.thesis.alumni.system.repository;


import com.thesis.alumni.system.model.Article;
import com.thesis.alumni.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Article findArticleBySlug(String slug);
}