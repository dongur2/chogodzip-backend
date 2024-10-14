package com.kb.community.mapper;

import com.kb.community.vo.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ArticleMapper {
    void insert(Article article);
    List<Article> findAll();
    Article findOneById(@Param("id") Long id);
}