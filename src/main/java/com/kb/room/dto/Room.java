package com.kb.room.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Room {
    private Long roomId;
    private Long userId;
    private Integer roomCnt;
    private String roomName;
    private Double floor;
    private String houseTypeCd;
    private String houseTypeNms;
    private String genderCd;
    private String tags;
    private String imgId;
    private String roomAddr;
    private String roomAddrFl;
    private Double roomLat;
    private Double roomLong;
    private Long depositMax;
    private Long depositMin;
    private Integer maintainCost;
    private Double priceMax;
    private Double priceMin;
    private String isSoldOut;
    private Date createdAt;
    private Date updatedAt;

}
