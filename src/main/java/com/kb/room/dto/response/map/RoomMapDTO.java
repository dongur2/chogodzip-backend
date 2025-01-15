package com.kb.room.dto.response.map;

import lombok.*;

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

    private Boolean canLoan;
    private List<String> loans;

    private Boolean isSoldOut;
    private Boolean isInterested;
}
