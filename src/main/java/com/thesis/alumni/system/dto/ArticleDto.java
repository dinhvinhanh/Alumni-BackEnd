package com.thesis.alumni.system.dto;

import com.thesis.alumni.system.enums.ArticleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleDto {
    private String title;
    private String content;
    private String thumbnail;
    private ArticleType status;
    private String slug;
    private String location;
    private Long categoryId;
}
