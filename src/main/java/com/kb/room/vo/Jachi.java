package com.kb.room.vo;

import lombok.*;

public class Jachi {
    private Room roomId; //FK
    private String CATEGORY;

    private Integer JACHI_ID;              // JACHI_ID

    //BASIC INFO ----
    private Integer priceMin;           // PRICE_MIN
    private Integer priceMax;           // PRICE_MAX
    private Integer depositMin;         // DEPOSIT_MIN
    private Integer depositMax;         // DEPOSIT_MAX
    private Integer maintenanceFee;     // MAINTENANCE_FEE
    private Double floor;               // FLOOR (층수)
    private Integer name;               // NAME (호수)
    private String roomType;            // ROOM_TYPE(원룸형 분리형 등)
    private String type;                // TYPe(원투룸, 오피스텔 구분)
    private Double roomSize;            // ROOM_SIZE
    private Double realSize;            // REAL_SIZE
    private RentType rentType;            // RENT_TYPE
    private Integer roomCnt;           // ROOM_CNT
    private Integer toiletCnt;        // TOILET_CNT
    private String sunDir;            // SUN_DIR
    private java.sql.Date avaMovinDate; // AVA_MOVIN_DATE
    private Byte avaMovinDir;         // AVA_MOVIN_DIR
    private Boolean parkAvail;           // PARK_AVAIL
    private String loanId;              // LOAN_ID

    private String description;     // DESCRIPTION
    private Double durationMin;     // DURATION_MIN

    //FACILITY INFO ----
    private String facilityHeating;   // FACILITY_HEATING
    private String facilityCooling;   // FACILITY_COOLING
    private String facilityLife;      // FACILITY_LIFE
    private String facilitySecurity;  // FACILITY_SECURITY

    //BUILDING INFO ----
    private Integer buildingType;        // BUILDING_TYPE
    private Integer canParking;          // CAN_PARKING
    private Integer hasElevator;         // HAS_ELEVATOR
    private java.sql.Date approveDate; // APPROVE_DATE

    private String isSoldOut;
}
