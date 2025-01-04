package com.kb.room.dto.response.detail;

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

    private String roomAddrFl;
}
