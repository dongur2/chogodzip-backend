package com.kb.room.dto.response;

import com.kb.room.vo.Room;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class RoomHomeMapDTO {
    private Integer roomId;
    private String type; // room.houseTypeNm
    private BigDecimal roomLat;
    private BigDecimal roomLong;

    //VO => DTO
    public static RoomHomeMapDTO from(Room vo) {
        return RoomHomeMapDTO.builder()
                .roomId(vo.getRoomId())
                .type(vo.getHouseTypeNm())
                .roomLat(vo.getRoomLat())
                .roomLong(vo.getRoomLong())
                .build();
    }
}
