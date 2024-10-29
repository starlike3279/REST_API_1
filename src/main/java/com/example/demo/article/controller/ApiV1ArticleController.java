package com.example.demo.article.controller;

import com.example.demo.article.dto.ArticleDTO;
import com.example.demo.article.entity.Article;
import com.example.demo.article.request.ArticleCreateRequest;
import com.example.demo.article.request.ArticleModifyRequest;
import com.example.demo.article.response.ArticleResponse;
import com.example.demo.article.service.ArticleService;
import com.example.demo.global.RsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ApiV1ArticleController {

    private final ArticleService articleService;

    @AllArgsConstructor
    @Getter
    public static class ArticlesResponse {

        private final List<ArticleDTO> articleList;

    }

    @GetMapping("")
    public RsData<ArticlesResponse> list() {

        List<ArticleDTO> articleList = new ArrayList<>();

        Article article1 = new Article("제목1", "내용1");
        articleList.add(new ArticleDTO(article1));

        Article article2 = new Article("제목2", "내용2");
        articleList.add(new ArticleDTO(article2));

        Article article3 = new Article("제목3", "내용3");
        articleList.add(new ArticleDTO(article3));

        return RsData.of("200", "게시글 다건 조회 성공", new ArticlesResponse(articleList));

    }

    @GetMapping("/{id}")
    public RsData<ArticleResponse> getArticle(@PathVariable("id") Long id) {

        Article article = new Article("제목1", "내용1");
        ArticleDTO articleDTO = new ArticleDTO(article);

        return RsData.of("200", "게시글 단건 조회 성공", new ArticleResponse(articleDTO));

    }

    @PostMapping("")
    public String create(@Valid @RequestBody ArticleCreateRequest articleCreateRequest) {

        System.out.println(articleCreateRequest.getSubject());
        System.out.println(articleCreateRequest.getContent());

        return "등록완료";

    }

    @PatchMapping("/{id}")
    public String modify(@PathVariable("id") Long id, @Valid @RequestBody ArticleModifyRequest articleModifyRequest) {

        System.out.println(id);
        System.out.println(articleModifyRequest.getSubject());
        System.out.println(articleModifyRequest.getContent());

        return "수정완료";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {

        System.out.println(id);

        return "삭제완료";

    }
}