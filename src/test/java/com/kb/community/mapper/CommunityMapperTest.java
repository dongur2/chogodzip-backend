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
                .title("[MAPPER TEST] 커뮤니티 제목")
                .content("[MAPPER TEST] 커뮤니티 본문")
                .tag("CTRV") //부동산
                .build();
        mapper.save(vo);

        Assertions.assertThat(vo.getCommunityId()).isNotNull();
    }

    @Test
    @DisplayName("커뮤니티 글 ID로 단일 조회")
    void findById() {
        Community vo = mapper.findById(1L);
        Assertions.assertThat(vo).isNotNull();
        Assertions.assertThat(vo.getTitle()).isEqualTo("[MAPPER TEST] 커뮤니티 제목");
        Assertions.assertThat(vo.getMemberName()).isEqualTo("둥기둥기"); //닉네임까지 조회되는지 확인
    }

    @Test
    @DisplayName("커뮤니티 글 전체 조회")
    void findAll() {
        List<Community> all = mapper.findAll();
        Assertions.assertThat(all.size()).isEqualTo(1);
        all.forEach(c -> log.info("{}의 작성자 {}", c.getTitle(), c.getMemberName()));
    }

    @Test
    @DisplayName("커뮤니티 글 삭제 처리")
    void updateIsDeletedByCommuniyId() {
        mapper.updateIsDeletedByCommunityId(2L); //삭제처리
        Community vo = mapper.findById(2L);
        Assertions.assertThat(vo.getIsDeleted()).isTrue();
    }

    @Test
    @DisplayName("커뮤니티 글 수정")
    void update() {
        Community vo = Community.builder()
                .mNo(24L)
                .title("[MAPPER TEST] 커뮤니티 제목 (수정 전)")
                .content("[MAPPER TEST] 커뮤니티 본문 (수정 전)")
                .tag("CTRV")
                .build();
        mapper.save(vo); //저장

        vo.setTitle("[MAPPER TEST] 커뮤니티 제목 (수정 후)");
        vo.setContent("[MAPPER TEST] 커뮤니티 본문 (수정 후)");
        vo.setTag("ITRV");

        mapper.update(vo); //업데이트

        Community updated = mapper.findById(vo.getCommunityId());
        Assertions.assertThat(updated.getTitle()).isEqualTo("[MAPPER TEST] 커뮤니티 제목 (수정 후)");
        Assertions.assertThat(updated.getContent()).isEqualTo("[MAPPER TEST] 커뮤니티 본문 (수정 후)");
        Assertions.assertThat(updated.getTag()).isEqualTo("ITRV");
    }
}