package com.kb.room.vo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jachi {

    private Long jachiId;           // JACHI_ID
    private Room room;            // ROOM_ID

    // Price and deposit information
    private Integer priceMin;       // PRICE_MIN
    private Integer priceMax;       // PRICE_MAX
    private Integer depositMin;     // DEPOSIT_MIN
    private Integer depositMax;     // DEPOSIT_MAX
    private Integer maintenanceFee; // MAINTENANCE_FEE

    // Room information
    private Double floor;           // FLOOR
    private String name;            // NAME
    private String roomType;        // ROOM_TYPE
    private String type;            // TYPE
    private Double roomSize;        // ROOM_SIZE
    private Double realSize;        // REAL_SIZE
    private Boolean rentType;       // RENT_TYPE
    private Double roomCnt;         // ROOM_CNT
    private Integer toiletCnt;      // TOILET_CNT
    private String sunDir;          // SUN_DIR
    private String avaMovinDate;    // AVA_MOVIN_DATE
    private Boolean avaMovinDir;    // AVA_MOVIN_DIR
    private Boolean parkAvail;      // PARK_AVAIL
    private Long loanId;            // LOAN_ID

    // Facility information
    private String facilityHeating; // FACILITY_HEATING
    private String facilityCooling; // FACILITY_COOLING
    private String facilityLife;    // FACILITY_LIFE
    private String facilitySecurity;// FACILITY_SECURITY

    // Building information
    private Double durationMin;     // DURATION_MIN
    private Boolean buildingType;   // BUILDING_TYPE
    private Boolean canParking;     // CAN_PARKING
    private Boolean hasElevator;    // HAS_ELEVATOR

    // Other information
    private String approveDate;     // APPROVE_DATE
    private Boolean isSoldOut;      // IS_SOLD_OUT
}
