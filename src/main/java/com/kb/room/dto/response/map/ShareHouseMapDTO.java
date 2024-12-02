package com.kb.room.dto.response.map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data @Builder
public class ShareHouseMapDTO {
    private Long shhId;
    private String title;

    private Integer priceMin;
    private Integer priceMax;
    private Integer depositMin;
    private Integer depositMax;
    private Integer maintenanceFee;

    private String genderLimit;
    private Integer ageMax;
    private Integer ageMin;

    private Long roomId;
    private Long userId;
    private Double roomLat;
    private Double roomLong;
    private String thumbnail;

    private String address;
    private String dongliNm;

    private String houseTypeCd;
    private Boolean contractMin;

    private Integer roomCnt;
    private Integer accomoCnt;

    // Facilities
    private String privateFacilities;
    private String services;
    private String languages;
    private String etc;

    private String facilityHeating;
    private String facilityCooling;
    private String facilityLife;
    private String facilitySecurity;

    private Boolean canLoan;

    // Building Information
    private Boolean buildingType;
    private Boolean canParking;
    private Boolean hasElevator;

    private String isSoldOut;

    private Date createdAt;

    //대출종류
    private List<String> loans;
}
