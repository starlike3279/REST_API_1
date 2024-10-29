package com.example.demo.article.response;

import com.example.demo.article.dto.ArticleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticlesResponse {

    private final ArticleDTO article;

}
