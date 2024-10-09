package com.kb.room.mapper;

import com.kb._config.RootConfig;
import com.kb._config.ServletConfig;
import com.kb._config.WebConfig;
import com.kb._config.WebMvcConfig;
import com.kb.room.dto.request.BasicInfo;
import com.kb.room.dto.request.GosiwonPostDTO;
import com.kb.room.dto.request.RoomTempPostDTO;
import com.kb.room.vo.RoomTemp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class, WebMvcConfig.class, ServletConfig.class})
class RoomMapperTest {
    @Autowired private RoomMapper mapper;

    @Test
    @DisplayName("ROOM 작성")
    void saveRoom() {
        RoomTempPostDTO dto = RoomTempPostDTO.builder()
                .userId(2L)
                .roomLat(new BigDecimal("37.0996527684079"))
                .roomLong(new BigDecimal("126.839982282494"))
                .thumbnail("https://image.neoflat.net/vgTmOpt30n25GpYp_-4_SqXjBDE=/240x288/filters:no_upscale():watermark(/resource/gobang.png,center,center,0,20,none)/house/2760/abf4686a-658e-4324-82a0-ff62863003a6.jpg")
                .canLoan(false)
                .build();

        Long saved = mapper.saveRoom(dto);
        Assertions.assertEquals(saved, 1L);
    }
}