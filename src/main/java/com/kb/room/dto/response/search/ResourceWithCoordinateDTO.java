package com.kb.room.dto.response.search;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class ResourceWithCoordinateDTO {
    String type; // sub, univ
    String name;
    String lat;
    String lng;
    String line;
}
