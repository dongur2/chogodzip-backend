package com.kb.room.dto.request;

import lombok.*;


@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class BuildingInfo {
    private String buildingType;
    private String canParking;
    private String hasElevator;
}
