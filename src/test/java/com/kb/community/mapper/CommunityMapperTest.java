package com.kb.community.mapper;

import com.kb._config.RootConfig;
import com.kb._config.ServletConfig;
import com.kb._config.WebConfig;
import com.kb._config.WebMvcConfig;
import com.kb.community.vo.Community;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;


@Slf4j
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class, WebMvcConfig.class, ServletConfig.class})
class CommunityMapperTest {
    @Autowired private CommunityMapper mapper;

    @Test
    @DisplayName("커뮤니티 글 작성")
    void save() {
        Community vo = Community.builder()
                .mNo(24L)
                .title("[MAPPER TEST] 커뮤니티 제목 6")
                .content("[MAPPER TEST] 커뮤니티 본문")
                .tag("RE") //부동산
                .build();
        mapper.save(vo);
    }

    @Test
    @DisplayName("커뮤니티 글 ID로 단일 조회")
    void findById() {
        Community vo = mapper.findById(6L);
        Assertions.assertThat(vo).isNotNull();
        Assertions.assertThat(vo.getTitle()).isEqualTo("[MAPPER TEST] 커뮤니티 제목 6");
        Assertions.assertThat(vo.getMemberName()).isEqualTo("둥기둥기"); //닉네임까지 조회되는지 확인
    }

    @Test
    @DisplayName("커뮤니티 글 전체 조회")
    void findAll() {
        List<Community> all = mapper.findAll();
        Assertions.assertThat(all.size()).isEqualTo(6);
        all.forEach(c -> log.info("{}의 작성자 {}", c.getTitle(), c.getMemberName()));
    }
}