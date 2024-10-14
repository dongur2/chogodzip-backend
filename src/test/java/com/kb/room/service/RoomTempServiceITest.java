package com.kb.room.service;

import com.kb._config.RootConfig;
import com.kb._config.ServletConfig;
import com.kb._config.WebConfig;
import com.kb._config.WebMvcConfig;
import com.kb.room.dto.request.*;
import com.kb.room.mapper.RoomMapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class, WebMvcConfig.class, ServletConfig.class})
class RoomTempServiceITest {
    @Autowired private RoomTempService service;
    @Autowired private RoomMapper mapper;

//    @Test
//    @DisplayName("고시원 매물 작성")
//    void addRoom() {
//        GosiwonPostDTO dto = GosiwonPostDTO.builder()
//                .category("gosiwon")
//                .basicInfo(
//                        BasicInfo.builder()
//                                .title("[테스트] 고시원01")
//                                .addr(new HashMap<String, Object>() {{
//                                    put("postcode", "08840");
//                                    put("address", "서울 동작구 노량진로 223");
//                                    put("detailAddress", "102930 2층");
//                                    put("roomLat", "37.4724714");
//                                    put("roomLong", "126.9419475");
//                                }})
//                                .price(new HashMap<String, Object>() {{
//                                    put("priceMin", "100");
//                                    put("priceMax", "120");
//                                    put("depositMin", "50");
//                                    put("depositMax", "60");
//                                    put("maintenanceFee", "25");
//                                }})
//                                .jachiElse(new HashMap<String, Object>() {{
//                                    put("contractMin", "3");
//                                    put("age", new HashMap<String, Object>() {{
//                                        put("ageMin", 0);
//                                        put("ageMax", 0);
//                                    }});
//                                    put("genderLimit", 1);
//                                }})
//                                .gosiwon(new HashMap<String, Object>() {{
//                                    put("type", "oneroomtel");
//                                }})
//                                .privateFacilities(new HashMap<String, Object>() {{
//                                    put("res", "priToilet|priShower");
//                                }})
//                                .services(new HashMap<String, Object>() {{
//                                    put("res", "securityCom|disinfectCom|cashReceipt|manlessDeliveryBox");
//                                }})
//                                .languages(new HashMap<String, Object>() {{
//                                    put("res", "eng|chn|jpn");
//                                }})
//                                .etc(new HashMap<String, Object>() {{
//                                    put("res", "allowMoveIn|allowForeigner");
//                                }})
//                                .desc("[테스트] 상세설명 \\('-')/--- *** ")
//                                .pics("https://image.neoflat.net/kcJBGm-XJcmfkGV4yZdfSTfEyAg=/240x288/filters:no_upscale():watermark(/resource/gobang.png,center,center,0,20,none)/house/10259/79da113e-19b9-4bb8-ac00-a7d02dea49c1.jpg")
//                                .build()
//                )
//                .loanInfo(
//                        LoanInfo.builder()
//                                .loans(new HashMap<String, Object>() {{
//                                    put("res", "1");
//                                }})
//                                .hasMortgage(null)
//                                .build()
//                )
//                .facilitiesInfo(
//                        FacilitiesInfo.builder()
//                                .facilityHeating(new HashMap<String, Object>() {{
//                                    put("res", "center");
//                                }})
//                                .facilityCooling(new HashMap<String, Object>() {{
//                                    put("res", "center|personal");
//                                }})
//                                .facilityLife(new HashMap<String, Object>() {{
//                                    put("res", "bed|closet|chair|table|tv");
//                                }})
//                                .facilitySecurity(new HashMap<String, Object>() {{
//                                    put("res", "digitLock|cctv|fireAlarm");
//                                }})
//                                .build()
//                )
//                .buildingInfo(
//                        BuildingInfo.builder()
//                                .buildingType("0")
//                                .canParking("false")
//                                .hasElevator("true")
//                                .build()
//                )
//                .build();
//
//        Long saved = service.addRoom(dto);
//        Assertions.assertThat(mapper.findOneByRoomId(saved).getTitle()).isEqualTo("[테스트] 고시원01");
//    }
}