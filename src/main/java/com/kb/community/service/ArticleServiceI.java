package com.kb.community.service;

import com.kb.community.mapper.ArticleMapper;
import com.kb.community.vo.Article;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j
@Service @Primary
@NoArgsConstructor @AllArgsConstructor
public class ArticleServiceI implements ArticleService{
    @Autowired private ArticleMapper mapper;

    //아티클 저장
    @Override @Transactional
    public void save(Article article) {
        try {
            mapper.insert(article);
        } catch (Exception e) { log.error("아티클 저장 실패: {}", e); }
    }

    //아티클 상제 조회
    @Override
    public Article getOne(Long id) {
        return mapper.findOneById(id);
    }

    //아티클 리스트 조회
    @Override
    public List<Article> getAll() {
        return mapper.findAll();
    }
}
