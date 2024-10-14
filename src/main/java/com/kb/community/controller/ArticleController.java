package com.kb.community.controller;

import com.kb.community.service.ArticleService;
import com.kb.community.vo.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/community/articles")
@PropertySource({"classpath:/application.properties"})
public class ArticleController {
    @Autowired private ArticleService articleService;

    @PostMapping
    public HttpStatus save(@RequestBody Article article) {
        articleService.save(article);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> get(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getOne(id));
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAll() {
        return ResponseEntity.ok(articleService.getAll());
    }
}
