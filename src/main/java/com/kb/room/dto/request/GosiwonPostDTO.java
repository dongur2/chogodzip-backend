package com.kb.room.dto.request;

import com.kb.room.vo.Gosiwon;
import com.kb.room.vo.RoomTemp;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;


@Setter @Getter
@Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class GosiwonPostDTO {
    private String category;
    private BasicInfo basicInfo;
    private LoanInfo loanInfo;
    private FacilitiesInfo facilitiesInfo;
    private BuildingInfo buildingInfo;

    //DTO => RoomVO
    public RoomTemp toRoomVO(Long userId) {
        return RoomTemp.builder()
                .userId(userId)
                .roomLat(new BigDecimal(basicInfo.getAddr().get("roomLat").toString()))
                .roomLong(new BigDecimal(basicInfo.getAddr().get("roomLong").toString()))
                //임시 썸네일 고정
                .thumbnail("https://image.neoflat.net/XE4sQE1a8q3_f-wusCuxHFfpbFk=/240x288/filters:no_upscale():watermark(/resource/gobang.png,center,center,0,20,none)/house/7095/768d02bd-f00e-45c1-b7a7-936b2f403736.jpg")
                .canLoan(loanInfo.getLoans().get("res") != null || !loanInfo.getLoans().get("res").toString().trim().isEmpty())
                .build();
    }

    //DTO => GosiwonVO
    public Gosiwon toGosiwonVO(RoomTemp room) {
        return Gosiwon.builder()
                .room(room)
                .category(category)
                .title(basicInfo.getTitle())
                .postcode(basicInfo.getAddr().get("postcode").toString())
                .address(basicInfo.getAddr().get("address").toString())
                .detailAddress(basicInfo.getAddr().get("detailAddress").toString())
                .priceMin(Integer.parseInt(basicInfo.getPrice().get("priceMin").toString()))
                .priceMax(Integer.parseInt(basicInfo.getPrice().get("priceMax").toString()))
                .depositMin(Integer.parseInt(basicInfo.getPrice().get("depositMin").toString()))
                .depositMax(Integer.parseInt(basicInfo.getPrice().get("depositMax").toString()))
                .maintenanceFee(Integer.parseInt(basicInfo.getPrice().get("maintenanceFee").toString()))
                .privateFacilities(basicInfo.getPrivateFacilities().toString())
                .services(basicInfo.getServices().toString())
                .languages(basicInfo.getLanguages().toString())
                .etc(basicInfo.getEtc().toString())
                .desc(basicInfo.getDesc())
                .pics(basicInfo.getPics())
                .genderLimit(Integer.parseInt(basicInfo.getJachiElse().get("genderLimit").toString()))
                .ageMin(Integer.parseInt(((Map<String, Object>)(basicInfo.getJachiElse().get("age"))).get("ageMin").toString()))
                .ageMax(Integer.parseInt(((Map<String, Object>)(basicInfo.getJachiElse().get("age"))).get("ageMax").toString()))
                .type(basicInfo.getGosiwon().get("type").toString().equals("gosiwon") ? 0:1)
                .contractMin(Integer.parseInt(basicInfo.getJachiElse().get("contractMin").toString()))
                .facilityHeating(facilitiesInfo.getFacilityHeating().get("res").toString())
                .facilityCooling(facilitiesInfo.getFacilityCooling().get("res").toString())
                .facilityLife(facilitiesInfo.getFacilityLife().get("res").toString())
                .facilitySecurity(facilitiesInfo.getFacilitySecurity().get("res").toString())
                .buildingType(Integer.parseInt(buildingInfo.getBuildingType()))
                .canParking(Boolean.parseBoolean(buildingInfo.getCanParking()) ? 1:0)
                .hasElevator(Boolean.parseBoolean(buildingInfo.getHasElevator()) ? 1:0)
                .isSoldOut(false)
                .build();
    }
}
