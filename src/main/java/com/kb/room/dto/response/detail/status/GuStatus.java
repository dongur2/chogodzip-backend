package com.kb.room.dto.response.detail.status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class GuStatus {
    private int maxPrice;
    private double avgPrice;
    private int minPrice;
}
