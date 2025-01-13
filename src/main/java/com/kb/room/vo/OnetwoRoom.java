package com.kb.room.vo;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class OnetwoRoom {
    private Long otrId;
    private Room room; //FK

    private String detailName; //호수
    private String roomType; //~형
}
