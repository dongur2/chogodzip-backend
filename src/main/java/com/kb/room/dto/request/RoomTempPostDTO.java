package com.kb.room.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Setter @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class RoomTempPostDTO {
    private Long userId;
    private BigDecimal roomLat;
    private BigDecimal roomLong;
    private String thumbnail;
    private Boolean canLoan;
}
