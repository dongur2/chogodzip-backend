package com.kb.room.dto;

import com.kb.interest.dto.InterestRoom;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GosiwonRoomDTO {
    // Gosiwon fields
    private Long gswId;
    private String title;
    private String postcode;
    private String address;
    private String detailAddress;
    private int priceMin;
    private int priceMax;
    private Integer depositMin;
    private Integer depositMax;
    private Integer maintenanceFee;
    private String privateFacilities;
    private String services;
    private String languages;
    private String etc;
    private String description;
    private String genderLimit;
    private Integer type;
    private boolean contractMin;
    private Integer ageMax;
    private Integer ageMin;
    private String facilityHeating;
    private String facilityCooling;
    private String facilityLife;
    private String facilitySecurity;
    private Boolean buildingType;
    private Boolean canParking;
    private Boolean hasElevator;
    private Boolean isSoldOut;

    // Room fields
    private Long roomId;
    private Long userId;
    private Double roomLat;
    private Double roomLong;
    private String thumbnail;
    private Boolean canLoan;
    private Date updatedAt;
    private Date createdAt;

}
