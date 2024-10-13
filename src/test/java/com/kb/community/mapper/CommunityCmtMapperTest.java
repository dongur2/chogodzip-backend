package com.kb.community.mapper;

import com.kb._config.RootConfig;
import com.kb._config.ServletConfig;
import com.kb._config.WebConfig;
import com.kb._config.WebMvcConfig;
import com.kb.community.vo.CommunityCmt;
import lombok.extern.log4j.Log4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@Log4j
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class, WebMvcConfig.class, ServletConfig.class})
class CommunityCmtMapperTest {
    @Autowired private CommunityCmtMapper mapper;

    @Test
    @DisplayName("댓글 작성")
    void saveCmt() {
        CommunityCmt vo = CommunityCmt.builder()
                .communityId(13L)
                .mNo(1L)
                .content("[매퍼 테스트] 댓글 써볼게요 4")
                .build();

        mapper.saveCmt(vo);
        Assertions.assertThat(mapper.findOneByCmtId(vo.getCmtId()).getContent()).isEqualTo("[매퍼 테스트] 댓글 써볼게요 4");
    }

    @Test
    @DisplayName("댓글 조회")
    void getAllByCommunityId() {
        List<CommunityCmt> voList = mapper.findAllByCommunityId(13L);
        Assertions.assertThat(voList.size()).isEqualTo(2);

        voList.forEach(c -> {
            log.info("댓글 작성자: " + c.getMemberName());
            log.info("댓글 내용: " + c.getContent());
        });
    }

    @Test
    @DisplayName("댓글 수정")
    void updateCmt() {
        mapper.updateCmt(2L, "[매퍼 테스트] 댓글 수정해볼께요 2");
        Assertions.assertThat(mapper.findOneByCmtId(2L).getContent()).isEqualTo("[매퍼 테스트] 댓글 수정해볼께요 2");
    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteCmt() {
        mapper.deleteCmtByCmtId(1L);
        Assertions.assertThat(mapper.findAllByCommunityId(13L).size()).isEqualTo(1);
    }
}