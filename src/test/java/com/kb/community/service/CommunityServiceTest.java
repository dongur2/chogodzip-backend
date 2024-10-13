package com.kb.community.service;

import com.kb._config.RootConfig;
import com.kb._config.ServletConfig;
import com.kb._config.WebConfig;
import com.kb._config.WebMvcConfig;
import com.kb.community.dto.request.CommunityPostDTO;
import com.kb.community.dto.response.CommunityDetailDTO;
import com.kb.community.dto.response.CommunityListDTO;
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
class CommunityServiceTest {
    @Autowired private CommunityService service;

    @Test
    @DisplayName("커뮤니티 글 작성")
    void add() {
        CommunityPostDTO dto = CommunityPostDTO.builder()
                .memberId("test")
                .title("[SERVICE TEST] 커뮤니티 제목")
                .content("[SERVICE TEST] 커뮤니티 본문")
                .tag("CTRV")
                .build();

        Long added = service.add(dto);
        Assertions.assertThat(added).isEqualTo(4L);
    }

    @Test
    @DisplayName("커뮤니티 상세 글 조회")
    void getDetail() {
        CommunityDetailDTO dto = service.getDetail(4L);
        Assertions.assertThat(dto.getTitle()).isEqualTo("[SERVICE TEST] 커뮤니티 제목");
    }

    @Test
    @DisplayName("커뮤니티 전체 글 조회 :: 삭제처리가 되지 않은 글만 조회")
    void getAll() {
        List<CommunityListDTO> all = service.getAll();
        Assertions.assertThat(all.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("커뮤니티 삭제 처리")
    void remove() {
        service.delete(4L);
        Assertions.assertThat(service.getAll().size()).isEqualTo(1);
    }
}