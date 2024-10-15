package com.kb.room.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter @Getter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class LoanInfo {
    private Map<String, Object> loans;
    private String hasMortgage;
}