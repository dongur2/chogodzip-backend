package com.kb.user.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @Builder
public class UserOptInfo {
    private Long optiId;
    private Long userId;
    private String realRegion;
    private String realDetail;
    private String interestSi;
    private String interestGu;
}
