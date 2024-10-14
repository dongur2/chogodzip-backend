package com.kb.room.vo;

import lombok.*;


@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Gosiwon {
    private Integer gswId;
    private Integer roomId; //FK

    //BASIC INFO ----
    private String title;
    private String postcode;
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
    private String genderLimit; // 0/1/2/3
    private String type; // gosiwon/oneroomtel
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
    private Boolean canParking;
    private Boolean hasElevator;

    private String isSoldOut;
}
