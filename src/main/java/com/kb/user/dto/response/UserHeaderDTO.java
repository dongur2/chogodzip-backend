package com.kb.user.dto.response;

import com.kb.user.dto.User;
import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class UserHeaderDTO {
    private Long userId;
    private String nickname;
    private String pic;
    private String interestSi;
    private String interestGu;

    public static UserHeaderDTO from(User user) {
        return UserHeaderDTO.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .pic(user.getPic())
                .interestSi("서울시")
                .interestGu(user.getInterestGu())
                .build();
    }
}