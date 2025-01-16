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

    private String genderLimit;      // GENDER_LIMIT
    private Integer ageMax;          // AGE_MAX
    private Integer ageMin;          // AGE_MIN

    private Integer accomoCnt;       // ACCOMO_CNT
    private Integer validRoomCnt;
}
