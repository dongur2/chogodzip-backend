package com.kb.room.vo;

import lombok.*;
import java.math.BigDecimal;
import java.util.Date;


@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Room {
    private Long roomId;
    private Long userId;
    private BigDecimal roomLat;
    private BigDecimal roomLong;
    private String thumbnail; //url
    private String address; //전체주소
    private String houseTypeCd; //HOUTP00001 (고시원), HOUTP00003 (원룸텔)
    private String houseTypeNm; //고시원, 원룸텔
    private String dongliNm; //크롤링데이터만 포함
    private String roomCnt; //공유주거에서 빈방개수
    private String roomName;
    private String roomAddrFl;
    private Boolean canLoan;
    private String postcode;

    private String privateFacilities;
    private String services;
    private String languages;
    private String etc;
    private String description;
    private String pics; //url

    private Integer priceMin;
    private Integer priceMax;
    private Integer depositMin;
    private Integer depositMax;
    private Integer maintenanceFee;
    private Integer contractMin;

    //FACILITY INFO ----
    private String facilityHeating;
    private String facilityCooling;
    private String facilityLife;
    private String facilitySecurity;

    //BUILDING INFO ----
    private String buildingType;
    private Boolean canParking;
    private Boolean hasElevator;

    private Boolean isSoldOut;

    private Date createdAt;
    private Date updatedAt;
}