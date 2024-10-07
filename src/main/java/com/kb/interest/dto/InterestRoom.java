package com.kb.interest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InterestRoom {
    private Long iroomId;
    private Long userId;
    private Long roomId;
    private String createdAt;
}
