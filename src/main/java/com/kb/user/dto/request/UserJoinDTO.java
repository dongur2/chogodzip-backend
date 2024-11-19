package com.kb.user.dto.request;


import com.kb.user.dto.User;
import com.kb.user.dto.UserOptInfo;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class UserJoinDTO {
    private String username;
    private String nickname;
    private String pic;
    private String loginType;
    private String realRegion;
    private String interestSi;
    private String interestGu;

    public User toUser() {
        return User.builder()
                .username(username)
                .nickname(nickname)
                .pic(pic)
                .loginType(loginType)
                .build();
    }

    public UserOptInfo toOptInfo() {
        return UserOptInfo.builder()
                .realRegion(realRegion)
                .interestSi(interestSi)
                .interestGu(interestGu)
                .build();
    }
}