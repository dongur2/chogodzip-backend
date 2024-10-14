package com.kb.community.service;

import com.kb.community.vo.Article;

import java.util.List;

public interface ArticleService {
    void save(Article article);
    Article getOne(Long id);
    List<Article> getAll();
}
