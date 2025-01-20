package com.kb.community.dto.response;

import lombok.*;

import java.util.Date;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class CommunityListDTO {
    private Long communityId;
    private String tag;
    private String title;
    private String nickname; //작성자 닉네임
    private Date createdAt;
    private int views;
}
