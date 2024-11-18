package com.kb.user.dto;

import lombok.*;


@Getter @Setter @Builder @AllArgsConstructor
public class TokenDTO {
    private String accessToken;
    private String refreshToken;
}
