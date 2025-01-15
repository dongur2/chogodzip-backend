package com.kb.room.dto.response.map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class OnetwoRoomMapDTO extends RoomMapDTO{
    private Long otrId;

    private String roomName;
    private String detailName; //호수

    private String roomType; //원룸타입/투룸/쓰리룸~

    private String roomAddrFl;
}
