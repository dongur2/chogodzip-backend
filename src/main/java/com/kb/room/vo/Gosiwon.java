package com.kb.room.vo;

import lombok.*;


@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Gosiwon {
    private Long gswId;
    private Room room; //FK

    private String title;
    private Integer priceMin;
    private Integer priceMax;
    private Integer depositMin;
    private Integer depositMax;
    private Integer maintenanceFee;
    private String genderLimit;
    private Integer ageMax;
    private Integer ageMin;
}
