package com.kb.room.dto.request;

import com.kb.room.vo.Gosiwon;
import com.kb.room.vo.Room;
import lombok.*;
import lombok.extern.log4j.Log4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Log4j
@Setter @Getter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class GosiwonPostDTO {
    private String category;
    private BasicInfo basicInfo;
    private LoanInfo loanInfo;
    private FacilitiesInfo facilitiesInfo;
    private BuildingInfo buildingInfo;
    private String writerId; //member.id


    //DTO => RoomVO
    public Room toRoomVO(Long userId, List<String> pics) {
        log.info("이미지 url: " + String.join("|", pics));

        return Room.builder()
                .userId(userId)
                .roomLat(new BigDecimal(basicInfo.getAddr().get("roomLat").toString()))
                .roomLong(new BigDecimal(basicInfo.getAddr().get("roomLong").toString()))
                .thumbnail(pics.get(0)) //추가된 사진 첫번째 사진을 자동 썸네일로 고정
                .pics(String.join("|", pics))
                .address(basicInfo.getAddr().get("address").toString())
                .houseTypeCd(basicInfo.getGosiwon().get("type").toString().equals("gosiwon") ? "HOUTP00001" : "HOUTP00003")
                .houseTypeNm(basicInfo.getGosiwon().get("type").toString().equals("gosiwon") ? "고시원" : "원룸텔")
                .canLoan(!loanInfo.getLoans().get("res").toString().equals("none"))
                .build();
    }

    //DTO => GosiwonVO
    public Gosiwon toGosiwonVO(Room room) {
        return Gosiwon.builder()
                .room(room) //FK

                .title(basicInfo.getTitle())
                .postcode(basicInfo.getAddr().get("postcode").toString())
                .priceMin(Integer.parseInt(basicInfo.getPrice().get("priceMin").toString()))
                .priceMax(Integer.parseInt(basicInfo.getPrice().get("priceMax").toString()))
                .depositMin(Integer.parseInt(basicInfo.getPrice().get("depositMin").toString()))
                .depositMax(Integer.parseInt(basicInfo.getPrice().get("depositMax").toString()))
                .maintenanceFee(Integer.parseInt(basicInfo.getPrice().get("maintenanceFee").toString()))
                .privateFacilities(basicInfo.getPrivateFacilities().get("res").toString())
                .services(basicInfo.getServices().get("res").toString())
                .languages(basicInfo.getLanguages().get("res").toString())
                .etc(basicInfo.getEtc().get("res").toString())
                .description(basicInfo.getDescription())
                .genderLimit(basicInfo.getJachiElse().get("genderLimit").toString())
                .type(basicInfo.getGosiwon().get("type").toString().equals("gosiwon") ? "0" : "1")
                .contractMin(Integer.parseInt(basicInfo.getContractMin().toString()))
                .ageMin(Integer.parseInt(((Map<String, Object>)(basicInfo.getJachiElse().get("age"))).get("ageMin").toString()))
                .ageMax(Integer.parseInt(((Map<String, Object>)(basicInfo.getJachiElse().get("age"))).get("ageMax").toString()))

                .facilityHeating(facilitiesInfo.getFacilityHeating().get("res").toString())
                .facilityCooling(facilitiesInfo.getFacilityCooling().get("res").toString())
                .facilityLife(facilitiesInfo.getFacilityLife().get("res").toString())
                .facilitySecurity(facilitiesInfo.getFacilitySecurity().get("res").toString())

                .buildingType(Integer.parseInt(buildingInfo.getBuildingType()))
                .canParking(Boolean.parseBoolean(buildingInfo.getCanParking()))
                .hasElevator(Boolean.parseBoolean(buildingInfo.getHasElevator()))

                .isSoldOut(Boolean.FALSE)
                .build();
    }
  
}
