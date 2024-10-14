package com.kb.room.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class JachiRoomDTO {
    // Jachiroom fields
    private Integer jachiId;
    private Integer priceMin;
    private Integer priceMax;
    private int depositMin;
    private int depositMax;
    private Integer maintenanceFee;
    private Double floor;               // FLOOR (층수)
    private Integer name;               // NAME (호수)
    private String roomType;           // ROOM_TYPE(원룸형 분리형 등)
    private String type;               // TYPE (원투룸, 오피스텔 구분)
    private Double roomSize;           // ROOM_SIZE
    private Double realSize;           // REAL_SIZE
    private String rentType;           // RENT_TYPE (RentType을 String으로 처리)
    private Integer roomCnt;           // ROOM_CNT
    private Integer toiletCnt;         // TOILET_CNT
    private String sunDir;             // SUN_DIR
    private Date avaMovinDate;         // AVA_MOVIN_DATE
    private Boolean avaMovinDir;       // AVA_MOVIN_DIR
    private Boolean parkAvail;         // PARK_AVAIL
    private String loanId;             // LOAN_ID
    private String description;        // DESCRIPTION
    private Double durationMin;        // DURATION_MIN

    private String facilityHeating;
    private String facilityCooling;
    private String facilityLife;
    private String facilitySecurity;

    private Boolean buildingType;
    private Boolean canParking;
    private Boolean hasElevator;
    private String isSoldOut;

    // Room fields
    private Long roomId;
    private Long userId;
    private Double roomLat;
    private Double roomLong;
    private String thumbnail;
    private Boolean canLoan;
    private java.util.Date updatedAt;
    private java.util.Date createdAt;
}
