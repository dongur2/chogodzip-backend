package com.kb.user.dto;

import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class UserHeaderDTO {
    private String nickname;
    private String pic;
    private String interestSi;
    private String interestGu;

    public static UserHeaderDTO from(User user) {
        return UserHeaderDTO.builder()
                .nickname(user.getNickname())
                .pic(user.getPic())
                .interestSi("서울시")
                .interestGu(user.getInterestGu())
                .build();
    }
}
