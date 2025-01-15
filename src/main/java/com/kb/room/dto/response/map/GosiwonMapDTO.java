package com.kb.room.dto.response.map;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class GosiwonMapDTO extends RoomMapDTO{
    private Long gswId;
    private String title;

    private String genderLimit;
    private Integer ageMax;
    private Integer ageMin;
}
