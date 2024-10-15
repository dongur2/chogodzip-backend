package com.kb.room.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class BuildingInfo {
    private String buildingType;
    private String canParking;
    private String hasElevator;
}