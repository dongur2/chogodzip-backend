package com.kb.room.dto.response.map;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data @Builder
public class GosiwonMapDTO {
    // Gosiwon fields
    private Long gswId;
    private String title;
    private Integer priceMin;
    private Integer priceMax;
    private Integer depositMin;
    private Integer depositMax;
    private Integer maintenanceFee;
    private String genderLimit;
    private Integer ageMax;
    private Integer ageMin;

    // Room fields
    private Long roomId;
    private Long userId;
    private Double roomLat;
    private Double roomLong;
    private String thumbnail;

    private String address;
    private String dongliNm;

    private String houseTypeCd;
    private Boolean contractMin;

    private String privateFacilities;
    private String services;
    private String languages;
    private String etc;

    private String facilityHeating;
    private String facilityCooling;
    private String facilityLife;
    private String facilitySecurity;

    private Boolean canLoan;

    private Boolean buildingType;
    private Boolean canParking;
    private Boolean hasElevator;

    private Boolean isSoldOut;

    private Date createdAt;

    //대출종류
    private List<String> loans;
}
