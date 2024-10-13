package com.kb.room.vo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShareHouse {

    private Long shareHouseId;       // SHAREHOUSE_ID
    private Room room;             // ROOM_ID

    // Price and deposit information
    private Integer priceMin;        // PRICE_MIN
    private Integer priceMax;        // PRICE_MAX
    private Integer depositMin;      // DEPOSIT_MIN
    private Integer depositMax;      // DEPOSIT_MAX
    private Integer maintenanceFee;  // MAINTENANCE_FEE

    // Room information
    private String genderLimit;      // GENDER_LIMIT
    private String type;             // TYPE
    private Boolean contractMin;     // CONTRACT_MIN
    private Integer ageMax;          // AGE_MAX
    private Integer ageMin;          // AGE_MIN
    private Integer roomCnt;         // ROOM_CNT
    private Integer accomoCnt;       // ACCOMO_CNT

    // Facilities
    private String privateFacilities;    // PRIVATE_FACILITIES
    private String services;             // SERVICES
    private String languages;            // LANGUAGES
    private String etc;                  // ETC
    private String description;          // DESCRIPTION
    private String facilityHeating;      // FACILITY_HEATING
    private String facilityCooling;      // FACILITY_COOLING
    private String facilityLife;         // FACILITY_LIFE
    private String facilitySecurity;     // FACILITY_SECURITY

    // Building information
    private Boolean buildingType;        // BUILDING_TYPE
    private Boolean canParking;          // CAN_PARKING
    private Boolean hasElevator;         // HAS_ELEVATOR

    // Sale status
    private String isSoldOut;            // IS_SOLD_OUT
}
