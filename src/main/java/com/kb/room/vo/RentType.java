package com.kb.room.vo;

import lombok.*;

@Getter
//@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor

public enum RentType {
    JEONSE,    // 전세
    BANJEONSE, // 반전세
    WOLSE      // 월세
}
