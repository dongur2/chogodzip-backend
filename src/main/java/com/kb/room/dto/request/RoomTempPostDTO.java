package com.kb.room.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter @Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class RoomTempPostDTO {
    private Long userId;
    private BigDecimal roomLat;
    private BigDecimal roomLong;
    private String thumbnail;
    private Boolean canLoan;
}
