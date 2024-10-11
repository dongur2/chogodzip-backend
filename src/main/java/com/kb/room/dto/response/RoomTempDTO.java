package com.kb.room.dto.response;

import com.kb.room.vo.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class RoomTempDTO {
    private Long roomId;
    private Long userId;

    //VO => DTO
    public static RoomTempDTO from(Room vo) {
        return RoomTempDTO.builder()
                .roomId(vo.getRoomId())
                .userId(vo.getUserId())
                .build();
    }
}
