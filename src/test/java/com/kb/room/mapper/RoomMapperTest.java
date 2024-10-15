package com.kb.room.mapper;

import com.kb._config.RootConfig;
import com.kb._config.ServletConfig;
import com.kb._config.WebConfig;
import com.kb._config.WebMvcConfig;
import com.kb.room.vo.Gosiwon;
import com.kb.room.vo.Room;
import com.kb.room.vo.RoomWithLoan;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Log4j
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class, WebMvcConfig.class, ServletConfig.class})
class RoomMapperTest {
    @Autowired private RoomMapper mapper;

    @Test
    @DisplayName("ROOM_ID로 부동산(ROOM) 조회")
    void findOneByRoomId() {
        Room room = mapper.findRoomByRoomId(100000);
        assertThat(room.getUserId()).isEqualTo(2L);
    }

    @Test
    @DisplayName("ROOM 작성")
    void saveRoom() {
        Room room = Room.builder()
                .userId(2L)
                .roomLat(new BigDecimal("37.0996527684079"))
                .roomLong(new BigDecimal("126.839982282494"))
                .thumbnail("https://image.neoflat.net/vgTmOpt30n25GpYp_-4_SqXjBDE=/240x288/filters:no_upscale():watermark(/resource/gobang.png,center,center,0,20,none)/house/2760/abf4686a-658e-4324-82a0-ff62863003a6.jpg")
                .pics("https://image.neoflat.net/vgTmOpt30n25GpYp_-4_SqXjBDE=/240x288/filters:no_upscale():watermark(/resource/gobang.png,center,center,0,20,none)/house/2760/abf4686a-658e-4324-82a0-ff62863003a6.jpg")
                .address("서울 광진구 능동로 195-1")
                .houseTypeCd("HOUTP00001")
                .houseTypeNm("고시원")
                .canLoan(false)
                .build();

        mapper.saveRoom(room);
        log.info("저장한 부동산 ID: " + room.getRoomId());
    }

    @Test
    @DisplayName("[대출 없음] 고시원 작성")
    void saveGosiwon() {
        Room room = Room.builder()
                .userId(24L)
                .roomLat(new BigDecimal("37.0996527684079"))
                .roomLong(new BigDecimal("126.839982282494"))
                .thumbnail("https://image.neoflat.net/vgTmOpt30n25GpYp_-4_SqXjBDE=/240x288/filters:no_upscale():watermark(/resource/gobang.png,center,center,0,20,none)/house/2760/abf4686a-658e-4324-82a0-ff62863003a6.jpg")
                .pics("https://image.neoflat.net/vgTmOpt30n25GpYp_-4_SqXjBDE=/240x288/filters:no_upscale():watermark(/resource/gobang.png,center,center,0,20,none)/house/2760/abf4686a-658e-4324-82a0-ff62863003a6.jpg")
                .address("서울 광진구 능동로 195-2")
                .houseTypeCd("HOUTP00001")
                .houseTypeNm("고시원")
                .canLoan(Boolean.FALSE)
                .build();

        mapper.saveRoom(room);

        Gosiwon gosiwon = Gosiwon.builder()
                .room(room)
                .title("[MAPPER TEST] 고시원 제목 1")
                .postcode("06903")
                .priceMin(40)
                .priceMax(65)
                .depositMin(10)
                .depositMax(15)
                .maintenanceFee(10)
                .privateFacilities("개인화장실|개인샤워실")
                .services("신용카드|식사제공")
                .languages("영어")
                .genderLimit("0")
                .type("0")
                .contractMin(31)
                .ageMin(0)
                .ageMax(0)
                .facilityHeating("중앙난방|개인난방")
                .facilityCooling("개인냉방")
                .facilityLife("책상|의자|식탁|TV")
                .facilitySecurity("CCTV|디지털 도어락")
                .buildingType(0)
                .canParking(Boolean.TRUE)
                .hasElevator(Boolean.TRUE)
                .isSoldOut(Boolean.FALSE)
                .build();

        mapper.saveGosiwon(gosiwon);

        assertThat(gosiwon.getRoom().getRoomId()).isEqualTo(room.getRoomId());
        assertThat(gosiwon.getTitle()).isEqualTo("[MAPPER TEST] 고시원 제목 1");
    }

    @Test
    @DisplayName("[대출 가능] 고시원 작성")
    void saveGosiwonWithLoan() {
        Room room = Room.builder()
                .userId(24L)
                .roomLat(new BigDecimal("37.0996527684079"))
                .roomLong(new BigDecimal("126.839982282494"))
                .thumbnail("https://image.neoflat.net/vgTmOpt30n25GpYp_-4_SqXjBDE=/240x288/filters:no_upscale():watermark(/resource/gobang.png,center,center,0,20,none)/house/2760/abf4686a-658e-4324-82a0-ff62863003a6.jpg")
                .pics("https://image.neoflat.net/vgTmOpt30n25GpYp_-4_SqXjBDE=/240x288/filters:no_upscale():watermark(/resource/gobang.png,center,center,0,20,none)/house/2760/abf4686a-658e-4324-82a0-ff62863003a6.jpg")
                .address("서울 광진구 능동로 195-2")
                .houseTypeCd("HOUTP00001")
                .houseTypeNm("고시원")
                .canLoan(Boolean.TRUE)
                .build();

        mapper.saveRoom(room);

        Gosiwon gosiwon = Gosiwon.builder()
                .room(room)
                .title("[MAPPER TEST] 고시원 제목 2 :: 대출 가능")
                .postcode("06903")
                .priceMin(40)
                .priceMax(65)
                .depositMin(10)
                .depositMax(15)
                .maintenanceFee(10)
                .privateFacilities("개인화장실|개인샤워실")
                .services("신용카드|식사제공")
                .languages("영어")
                .genderLimit("0")
                .type("0")
                .contractMin(31)
                .ageMin(0)
                .ageMax(0)
                .facilityHeating("중앙난방|개인난방")
                .facilityCooling("개인냉방")
                .facilityLife("책상|의자|식탁|TV")
                .facilitySecurity("CCTV|디지털 도어락")
                .buildingType(0)
                .canParking(Boolean.TRUE)
                .hasElevator(Boolean.TRUE)
                .isSoldOut(Boolean.FALSE)
                .build();

        mapper.saveGosiwon(gosiwon);

        //대출
        if(room.getCanLoan()) {
            log.info("대출 가능");
            RoomWithLoan rwl = RoomWithLoan.builder()
                    .roomId(room.getRoomId())
                    .loanId(1L) //버팀목
                    .build();

            mapper.saveRoomWithLoan(rwl);
        }
    }

    @Test
    @DisplayName("관심 지역 매물 검색")
    void fetchRoomsAtInterestArea() {
        String area = "서울 광진구";

        List<Gosiwon> latestFourAtInterestArea = mapper.findLatestFourAtInterestArea(area);
        for(Gosiwon gosiwon : latestFourAtInterestArea) {
            log.info(gosiwon.getTitle());
        }
    }
}