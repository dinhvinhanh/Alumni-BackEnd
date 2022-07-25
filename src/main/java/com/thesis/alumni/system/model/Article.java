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

    public Article(String name){
        super();
    }

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "published_ad")
    private String publishedAt;

    @Column(name = "url_to_image")
    private String urlToImage;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}

