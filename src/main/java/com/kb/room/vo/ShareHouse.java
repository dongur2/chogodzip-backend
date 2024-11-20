package com.kb.room.vo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShareHouse {
    private Long shhId;
    private Room room;

    private String title;

    private Integer priceMin;        // PRICE_MIN
    private Integer priceMax;        // PRICE_MAX
    private Integer depositMin;      // DEPOSIT_MIN
    private Integer depositMax;      // DEPOSIT_MAX
    private Integer maintenanceFee;  // MAINTENANCE_FEE
    private String genderLimit;      // GENDER_LIMIT
    private Integer ageMax;          // AGE_MAX
    private Integer ageMin;          // AGE_MIN

    private Integer accomoCnt;       // ACCOMO_CNT
}
