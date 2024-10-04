package com.kb.search.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class University {

    private int universityId;
    private String universityName;
    private Double universityLat;
    private Double universityLong;
    private String type;

}
