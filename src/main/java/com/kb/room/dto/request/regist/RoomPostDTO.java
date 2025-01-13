package com.kb.room.dto.request.regist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kb.room.vo.Gosiwon;
import com.kb.room.vo.OnetwoRoom;
import com.kb.room.vo.Room;
import com.kb.room.vo.ShareHouse;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter @Getter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class RoomPostDTO {
    private String category;
    private BasicInfo basicInfo;
    private LoanInfo loanInfo;
    private FacilitiesInfo facilitiesInfo;
    private BuildingInfo buildingInfo;

    public Room toRoomVO(Long userId, List<String> pics) {
        return Room.builder()
                .userId(userId)
                .roomLat(new BigDecimal(basicInfo.roomLat))
                .roomLong(new BigDecimal(basicInfo.roomLng))
                .thumbnail(pics.get(0))
                .address(basicInfo.address)
                .houseTypeCd(basicInfo.type)
                .houseTypeNm(convertHouseTypeCodeToName(basicInfo.type))

                .canLoan(!loanInfo.loans.isEmpty())
                .postcode(basicInfo.postcode)

                .privateFacilities(String.join("|", basicInfo.privateFacilities))
                .services(String.join("|", basicInfo.services))
                .languages(String.join("|", basicInfo.languages))
                .etc(String.join("|", basicInfo.etc))
                .description(basicInfo.description)
                .pics(String.join("|", pics))

                .priceMin(basicInfo.priceMin)
                .priceMax(basicInfo.priceMax)
                .depositMin(basicInfo.depositMin)
                .depositMax(basicInfo.depositMax)
                .maintenanceFee(basicInfo.maintenanceFee)
                .contractMin(basicInfo.contractMin)

                .facilityHeating(String.join("|", facilitiesInfo.facilityHeating))
                .facilityCooling(String.join("|", facilitiesInfo.facilityCooling))
                .facilityLife(String.join("|", facilitiesInfo.facilityLife))
                .facilitySecurity(String.join("|", facilitiesInfo.facilitySecurity))

                .buildingType(buildingInfo.buildingType)
                .canParking(buildingInfo.canParking)
                .hasElevator(buildingInfo.hasElevator)

                .isSoldOut(false)
                .build();
    }

    public Gosiwon toGosiwonVO(Room room) {
        return Gosiwon.builder()
                .room(room)
                .title(basicInfo.title)
                .genderLimit(basicInfo.genderLimit)
                .ageMax(basicInfo.ageMax)
                .ageMin(basicInfo.ageMin)
                .build();
    }

    public OnetwoRoom toOneTwoRoomVO(Room room) {
        return OnetwoRoom.builder()
                .room(room)
                .detailName(basicInfo.detailAddress)
                .roomType(basicInfo.roomCntType)
                .build();
    }

    public ShareHouse toShareHouseVO(Room room) {
        return ShareHouse.builder()
                .room(room)
                .title(basicInfo.title)
                .genderLimit(basicInfo.genderLimit)
                .ageMax(basicInfo.ageMax)
                .ageMin(basicInfo.ageMin)
                .accomoCnt(basicInfo.accomoCnt)
                .build();
    }

    private String convertHouseTypeCodeToName(String type) {
        return switch (type) {
            case "HOUTP00001" -> "고시원";
            case "HOUTP00002" -> "쉐어하우스";
            case "HOUTP00003" -> "원룸텔";
            case "HOUTP00004" -> "코리빙하우스";
            case "HOUTP00005" -> "게스트하우스";
            case "HOUTP00008" -> "원･투룸";
            case "HOUTP00009" -> "오피스텔";
            default -> throw new IllegalStateException("잘못된 값입니다: " + type);
        };
    }

    @Setter @ToString
    private static class BasicInfo {
        private String title;
        private String postcode;
        private String address;
        private String detailAddress;
        private String roomLat;
        private String roomLng;

        private String type;
        private String rentType; //원투룸

        private Integer priceMin;
        private Integer priceMax;
        private Integer depositMin;
        private Integer depositMax;
        private Integer maintenanceFee;

        private Integer contractMin;

        //원투룸
        private Double thisFloor;
        private Double totalFloor;
        private Double privateArea;
        private Double totalArea;
        private String roomCntType;

        //고시원+공유
        private Integer ageMin;
        private Integer ageMax;
        private String genderLimit;

        //공유
        private Integer validRoomCnt;
        private Integer accomoCnt;

        //시설
        private List<String> privateFacilities;
        private List<String> services;
        private List<String> languages;
        private List<String> etc;

        private String description;
        private String pics;
    }

    @Setter @Getter @ToString
    public static class LoanInfo {
        private List<Integer> loans;
        private Boolean hasMortgage;
    }

    @Setter @ToString
    private static class FacilitiesInfo {
        private List<String> facilityHeating;
        private List<String> facilityCooling;
        private List<String> facilityLife;
        private List<String> facilitySecurity;
    }

    @Setter @ToString
    private static class BuildingInfo {
        private String buildingType;
        private Boolean canParking;
        private Boolean hasElevator;
    }
}
