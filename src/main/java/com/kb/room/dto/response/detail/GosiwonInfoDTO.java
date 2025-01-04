package com.kb.room.dto.response.detail;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class GosiwonInfoDTO extends RoomDetailInfoDTO{
    private Long gswId;
    private String title;

    private String genderLimit;
    private Integer ageMax;
    private Integer ageMin;
}
