package com.kb.room.vo;

import lombok.*;


@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Gosiwon {
    private Long gswId;
    private Room room; //FK

    private String title;
    private String genderLimit;
    private Integer ageMax;
    private Integer ageMin;
}
