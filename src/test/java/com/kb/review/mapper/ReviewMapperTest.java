package com.kb.review.mapper;

import com.kb._config.RootConfig;
import com.kb._config.ServletConfig;
import com.kb._config.WebConfig;
import com.kb._config.WebMvcConfig;
import com.kb.review.vo.UserReview;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class, WebMvcConfig.class, ServletConfig.class})
class ReviewMapperTest {
    @Autowired private ReviewMapper mapper;

    @Test
    void findAll() {
        List<UserReview> all = mapper.findAll();
    }

    @Test
    void findAllByRoomId() {
        List<UserReview> all = mapper.findAllByRoomId(1461L);
        Assertions.assertThat(all.size()).isEqualTo(2);
    }
}