package com.kb.room.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter @Getter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class BasicInfo {
    private String title;
    private Map<String, Object> addr;
    private Map<String, Object> price;
    private Map<String, Object> age;
    private Map<String, Object> jachiElse;
    private Map<String, Object> gosiwon;
    private Map<String, Object> privateFacilities;
    private Map<String, Object> services;
    private Map<String, Object> languages;
    private Map<String, Object> etc;
    private Integer contractMin;
    private String description;
    private String pics;
}
