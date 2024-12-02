package com.kb.room.dto.response.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data @Builder
public class CoordinateDTO {
    String lat;
    String lng;
}
