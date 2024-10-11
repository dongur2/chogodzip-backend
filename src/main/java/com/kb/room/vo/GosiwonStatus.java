package com.kb.room.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GosiwonStatus {
    private int maxPrice;
    private double avgPrice;
    private int minPrice;
}
