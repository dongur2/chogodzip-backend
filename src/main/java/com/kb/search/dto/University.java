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

    private int unisubId;
    private String name;
    private Double lat;
    private Double lng;
    private String line;
    private String type;

}
