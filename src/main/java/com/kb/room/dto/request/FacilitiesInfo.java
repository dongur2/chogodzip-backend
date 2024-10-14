package com.kb.room.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter @Getter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class FacilitiesInfo {
    private Map<String, Object> facilityHeating;
    private Map<String, Object> facilityCooling;
    private Map<String, Object> facilityLife;
    private Map<String, Object> facilitySecurity;
}