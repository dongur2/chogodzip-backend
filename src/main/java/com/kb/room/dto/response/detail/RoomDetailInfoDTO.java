package com.kb.room.dto.response.detail;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public abstract class RoomDetailInfoDTO {
    private Long roomId;
    private Long userId;
    private Double roomLat;
    private Double roomLong;
    private String thumbnail; //url
    private String address; //전체주소

    private String houseTypeCd; //HOUTP00001 (고시원), HOUTP00003 (원룸텔)
    private String houseTypeNm; //고시원, 원룸텔

    private String roomAddrFl;

    private Integer priceMin;
    private Integer priceMax;
    private Integer depositMin;
    private Integer depositMax;
    private Integer maintenanceFee;

    private String privateFacilities;
    private String services;
    private String languages;
    private String etc;
    private String description;
    private String pics; //url

    private Integer contractMin;

    //FACILITY INFO ----
    private String facilityHeating;
    private String facilityCooling;
    private String facilityLife;
    private String facilitySecurity;

    //BUILDING INFO ----
    private Integer buildingType;
    private Boolean canParking;
    private Boolean hasElevator;

    private Date createdAt;
    private Date updatedAt;

    private Integer interestCnt;

    private Boolean canLoan;
    private List<String> loans;

    private Boolean isSoldOut;
    private Boolean isInterested;
}
