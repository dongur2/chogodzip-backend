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
public class OnetwoRoomMapDTO {
    private Long otrId;

    private Integer priceMin;
    private Integer priceMax;
    private Integer depositMin;
    private Integer depositMax;
    private Integer maintenanceFee;
    private String detailName; //호수
    private String roomType; //~형

    private Long roomId;
    private Long userId;
    private Double roomLat;
    private Double roomLong;
    private String thumbnail;

    private String address;
    private String dongliNm;

    private String houseTypeCd;

    private String roomCnt;
    private String roomName;
    private String roomAddrFl;

    private Boolean canLoan;

    private String privateFacilities;
    private String services;
    private String languages;
    private String etc;

    private String facilityHeating;
    private String facilityCooling;
    private String facilityLife;
    private String facilitySecurity;

    private Boolean buildingType;
    private Boolean canParking;
    private Boolean hasElevator;

    private String isSoldOut;

    private Date createdAt;

    private List<String> loans;

}
