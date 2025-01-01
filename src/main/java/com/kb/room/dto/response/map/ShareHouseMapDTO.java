package com.kb.room.dto.response.map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class ShareHouseMapDTO extends RoomMapDTO{
    private Long shhId;
    private String title;

    private String genderLimit;
    private Integer ageMax;
    private Integer ageMin;

    private Boolean contractMin;

    private Integer roomCnt;
    private Integer accomoCnt;
}
