package com.thesis.alumni.system.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String slug;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "category", cascade = CascadeType.MERGE)
    @JsonIgnoreProperties("category")
    private Collection<Article> articles;
}
