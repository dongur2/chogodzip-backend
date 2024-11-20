package com.kb.room.dto.response;

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

    // Price and deposit information
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
    private Boolean contractMin;        // CONTRACT_MIN

    private Integer roomCnt;            // ROOM_CNT
    private Integer accomoCnt;          // ACCOMO_CNT

    // Facilities
    private String privateFacilities;   // PRIVATE_FACILITIES
    private String services;            // SERVICES
    private String languages;           // LANGUAGES
    private String etc;                 // ETC

    private String facilityHeating;     // FACILITY_HEATING
    private String facilityCooling;     // FACILITY_COOLING
    private String facilityLife;        // FACILITY_LIFE
    private String facilitySecurity;    // FACILITY_SECURITY

    private Boolean canLoan;

    // Building information
    private Boolean buildingType;       // BUILDING_TYPE
    private Boolean canParking;         // CAN_PARKING
    private Boolean hasElevator;        // HAS_ELEVATOR

    // Sale status
    private String isSoldOut;           // IS_SOLD_OUT

    // Room fields
    private Date createdAt;

    //대출종류
    private List<String> loans;
}
