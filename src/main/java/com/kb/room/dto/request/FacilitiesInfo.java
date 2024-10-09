package com.kb.room.dto.request;

import lombok.*;

import java.util.Map;

@Setter @Getter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class FacilitiesInfo {
    private Map<String, Object> facilityHeating;
    private Map<String, Object> facilityCooling;
    private Map<String, Object> facilityLife;
    private Map<String, Object> facilitySecurity;
}