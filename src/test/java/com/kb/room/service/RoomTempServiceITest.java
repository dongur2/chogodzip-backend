package com.kb.room.service;

import com.kb._config.RootConfig;
import com.kb._config.ServletConfig;
import com.kb._config.WebConfig;
import com.kb._config.WebMvcConfig;
import com.kb.room.dto.request.*;
import com.kb.room.mapper.RoomMapper;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;


@Log4j
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class, WebMvcConfig.class, ServletConfig.class})
class RoomTempServiceITest {
    @Autowired private RoomTempService roomTempService;
    @Autowired private RoomMapper mapper;

    @Test
    @DisplayName("고시원 매물 작성")
    void addRoom() {
        GosiwonPostDTO dto = GosiwonPostDTO.builder()
                .category("gosiwon")
                .basicInfo(
                        BasicInfo.builder()
                                .title("[SERVICE TEST] 고시원 01")
                                .addr(new HashMap<String, Object>() {{
                                    put("postcode", "08840");
                                    put("address", "서울 동작구 노량진로 223");
                                    put("roomLat", "37.4724714");
                                    put("roomLong", "126.9419475");
                                }})
                                .price(new HashMap<String, Object>() {{
                                    put("priceMin", "100");
                                    put("priceMax", "120");
                                    put("depositMin", "50");
                                    put("depositMax", "60");
                                    put("maintenanceFee", "25");
                                }})
                                .jachiElse(new HashMap<String, Object>() {{
                                    put("age", new HashMap<String, Object>() {{
                                        put("ageMin", 0);
                                        put("ageMax", 0);
                                    }});
                                    put("genderLimit", 1);
                                }})
                                .contractMin(30)
                                .gosiwon(new HashMap<String, Object>() {{
                                    put("type", "oneroomtel");
                                }})
                                .privateFacilities(new HashMap<String, Object>() {{
                                    put("res", "개인화장실|개인샤워실");
                                }})
                                .services(new HashMap<String, Object>() {{
                                    put("res", "방역업체|현금영수증|무인택배함");
                                }})
                                .languages(new HashMap<String, Object>() {{
                                    put("res", "영어|중국어|일본어");
                                }})
                                .etc(new HashMap<String, Object>() {{
                                    put("res", "주소이전|외국인 가능");
                                }})
                                .description("[테스트] 상세설명 \\('-')/--- *** ")
                                .pics("https://image.neoflat.net/kcJBGm-XJcmfkGV4yZdfSTfEyAg=/240x288/filters:no_upscale():watermark(/resource/gobang.png,center,center,0,20,none)/house/10259/79da113e-19b9-4bb8-ac00-a7d02dea49c1.jpg")
                                .build()
                )
                .loanInfo(
                        LoanInfo.builder()
                                .loans(new HashMap<String, Object>() {{
                                    put("res", "1");
                                }})
                                .hasMortgage(null)
                                .build()
                )
                .facilitiesInfo(
                        FacilitiesInfo.builder()
                                .facilityHeating(new HashMap<String, Object>() {{
                                    put("res", "중앙난방");
                                }})
                                .facilityCooling(new HashMap<String, Object>() {{
                                    put("res", "중앙냉방|개인냉방");
                                }})
                                .facilityLife(new HashMap<String, Object>() {{
                                    put("res", "침대|책상|의자|식탁|TV");
                                }})
                                .facilitySecurity(new HashMap<String, Object>() {{
                                    put("res", "디지털 도어락|CCTV|화재 경보 시스템");
                                }})
                                .build()
                )
                .buildingInfo(
                        BuildingInfo.builder()
                                .buildingType("0")
                                .canParking("false")
                                .hasElevator("true")
                                .build()
                )
                .writerId("mimmmji")
                .build();
    }
}