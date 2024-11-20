package com.kb.room.vo;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class OnetwoRoom {
    private Long otrId;
    private Room room; //FK

    private Integer priceMin;
    private Integer priceMax;
    private Integer depositMin;
    private Integer depositMax;
    private Integer maintenanceFee;
    private String detailName; //호수
    private String roomType; //~형
}
