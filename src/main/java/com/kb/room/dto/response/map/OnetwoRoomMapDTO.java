package com.kb.room.dto.response.map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class OnetwoRoomMapDTO extends RoomMapDTO{
    private Long otrId;

    private String detailName; //호수
    private String roomType; //~형

    private String roomCnt;
    private String roomName;
    private String roomAddrFl;
}
