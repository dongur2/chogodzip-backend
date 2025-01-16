package com.kb.room.dto.response.detail.info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class OnetwoRoomInfoDTO extends RoomDetailInfoDTO{
    private Long otrId;

    private String roomName;
    private String detailName; //호수
    private String roomType; //원룸타입/투룸/쓰리룸~
    private String rentType;
    private Double thisFl;
    private Integer totalFl;
    private Double pvArea;
    private Double ttArea;
    private Boolean hasMortgage;
}
