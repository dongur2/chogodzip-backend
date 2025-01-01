package com.kb.room.dto.response.map;

import lombok.*;

import java.util.Date;
import java.util.List;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public abstract class RoomMapDTO {
    private Long roomId;
    private Long userId;
    private Double roomLat;
    private Double roomLong;
    private String thumbnail;

    private String address;
    private String dongliNm;
    private String houseTypeCd;

    private Integer priceMin;
    private Integer priceMax;
    private Integer depositMin;
    private Integer depositMax;
    private Integer maintenanceFee;

    // Facilities
    private String privateFacilities;
    private String services;
    private String languages;
    private String etc;

    private String facilityHeating;
    private String facilityCooling;
    private String facilityLife;
    private String facilitySecurity;

    // Building Information
    private Boolean buildingType;
    private Boolean canParking;
    private Boolean hasElevator;

    private Boolean canLoan;
    private List<String> loans;

    private String isSoldOut;
    private Boolean isInterested;

    private Date createdAt;
}
