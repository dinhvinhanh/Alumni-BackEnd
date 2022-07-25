package com.thesis.alumni.system.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@Entity
@Table(name = "articles")
@EntityListeners(AuditingEntityListener.class)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Article(){};

    public Article(String title){
        super();
    }

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "created_ad")
    private String createdAt;

    @Column(name = "updated_ad")
    private String updatedAt;

    private String urlToImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}

