package com.kb.room.vo.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data @Builder
public class Subway {
    private String code;
    private String name;
    private Double sbwLat;
    private Double sbwLong;
    private String line;
    private String exCode;
}
