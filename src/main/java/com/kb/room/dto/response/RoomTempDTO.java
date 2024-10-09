package com.kb.room.dto.response;

import com.kb.room.vo.RoomTemp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
@AllArgsConstructor
public class RoomTempDTO {
    private Long roomId;
    private Long userId;

    //VO => DTO
    public static RoomTempDTO from(RoomTemp vo) {
        return RoomTempDTO.builder()
                .roomId(vo.getRoomId())
                .userId(vo.getUserId())
                .build();
    }
}
