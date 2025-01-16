package com.kb.room.vo;

import lombok.*;


@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class OnetwoRoom {
    private Long otrId;
    private Room room; //FK

    private String detailName; //호수
    private String roomType; //~형
    private String rentType; //전월세
    private Double thisFl;
    private Integer totalFl;
    private Double pvArea;
    private Double ttArea;

    private Boolean hasMortgage; //융자유무
}
