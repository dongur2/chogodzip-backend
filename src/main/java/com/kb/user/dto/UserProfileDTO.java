package com.kb.user.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class UserProfileDTO {
    private String nickname;
    private String pic;
    private String realRegion;
    private String interestSi;
    private String interestGu;

    public static UserProfileDTO from(User user, String region) {
        return UserProfileDTO.builder()
                .nickname(user.getNickname())
                .pic(user.getPic())
                .realRegion(region)
                .interestSi("서울시")
                .interestGu(user.getInterestGu())
                .build();
    }
}
