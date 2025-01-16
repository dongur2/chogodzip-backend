package com.kb.room.dto.response.detail.info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ShareHouseInfoDTO extends RoomDetailInfoDTO{
    private Long shhId;
    private String title;

    private String genderLimit;
    private Integer ageMax;
    private Integer ageMin;

    private Integer contractMin;

    private Integer roomCnt;
    private Integer accomoCnt;
}
