package com.kb.room.vo;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Gosiwon {
    private RoomTemp room;
    private String category;

    private Long gswId;

    //BASIC INFO ----
    private String title;
    private String postcode;
    private String address;
    private String detailAddress;
    private Integer priceMin;
    private Integer priceMax;
    private Integer depositMin;
    private Integer depositMax;
    private Integer maintenanceFee;
    private String privateFacilities;
    private String services;
    private String languages;
    private String etc;
    private String desc;
    private String pics;
    private Integer genderLimit;
    private Integer type;
    private Integer contractMin;
    private Integer ageMax;
    private Integer ageMin;

    //FACILITY INFO ----
    private String facilityHeating;
    private String facilityCooling;
    private String facilityLife;
    private String facilitySecurity;

    //BUILDING INFO ----
    private Integer buildingType;
    private Integer canParking;
    private Integer hasElevator;

    private Boolean isSoldOut;
}
