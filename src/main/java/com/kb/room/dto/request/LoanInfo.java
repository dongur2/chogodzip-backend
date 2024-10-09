package com.kb.room.dto.request;

import lombok.*;

import java.util.Map;

@Setter @Getter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class LoanInfo {
    private Map<String, Object> loans;
    private String hasMortgage;
}
