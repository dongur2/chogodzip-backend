package com.kb.room.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class RoomTemp {
    private Long roomId;
    private Long userId;
    private BigDecimal roomLat;
    private BigDecimal roomLong;
    private String thumbnail;
    private Boolean canLoan;
    private Date createdAt;
    private Date updatedAt;
}
