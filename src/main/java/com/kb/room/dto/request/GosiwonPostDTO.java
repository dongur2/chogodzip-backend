package com.kb.room.dto.request;

import com.kb.room.vo.Gosiwon;
import com.kb.room.vo.RoomTemp;
import lombok.*;

import java.math.BigDecimal;


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
                .priceMin((Integer) basicInfo.getPrice().get("priceMin"))
                .priceMax((Integer) basicInfo.getPrice().get("priceMax"))
                .depositMin((Integer) basicInfo.getPrice().get("depositMin"))
                .depositMax((Integer) basicInfo.getPrice().get("depositMax"))
                .maintenanceFee((Integer) basicInfo.getPrice().get("maintenanceFee"))
                .privateFacilities(basicInfo.getPrivateFacilities().toString())
                .services(basicInfo.getServices().toString())
                .languages(basicInfo.getLanguages().toString())
                .etc(basicInfo.getEtc().toString())
                .pics(basicInfo.getPics())
                .genderLimit((Integer) basicInfo.getJachiElse().get("genderLimit"))
                .ageMin((Integer) basicInfo.getAge().get("ageMin"))
                .ageMax((Integer) basicInfo.getAge().get("ageMax"))
                .facilityHeating(facilitiesInfo.getFacilityHeating().toString())
                .facilityCooling(facilitiesInfo.getFacilityCooling().toString())
                .facilityLife(facilitiesInfo.getFacilityLife().toString())
                .facilitySecurity(facilitiesInfo.getFacilitySecurity().toString())
                .buildingType(Integer.parseInt(buildingInfo.getBuildingType()))
                .canParking(Integer.parseInt(buildingInfo.getCanParking()))
                .hasElevator(Integer.parseInt(buildingInfo.getHasElevator()))
                .isSoldOut(false)
                .build();
    }
}
