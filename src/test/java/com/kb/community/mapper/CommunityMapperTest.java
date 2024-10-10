package com.kb.community.mapper;

import com.kb._config.RootConfig;
import com.kb._config.ServletConfig;
import com.kb._config.WebConfig;
import com.kb._config.WebMvcConfig;
import com.kb.community.vo.Community;
import com.kb.member.dto.Member;
import com.kb.member.mapper.MemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class, WebMvcConfig.class, ServletConfig.class})
class CommunityMapperTest {
    @Autowired private CommunityMapper mapper;
    @Autowired private MemberMapper memberMapper;

    @Test
    @DisplayName("커뮤니티 글 작성")
    void save() {
        Community vo = Community.builder()
                .user(memberMapper.selectById("1L"))
                .title("[테스트] 커뮤니티 제목 01")
                .content("[테스트] 커뮤니티 컨텐츠 01")
                .tag("부동산")
                .build();
        mapper.save(vo);

        Community saved = mapper.findById(vo.getCommunityId());
        Assertions.assertThat(saved.getTitle()).isEqualTo("[테스트] 커뮤니티 제목 01");
    }
}