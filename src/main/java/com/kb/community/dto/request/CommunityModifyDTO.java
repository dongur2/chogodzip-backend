package com.kb.community.dto.request;

import lombok.*;

@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class CommunityModifyDTO {
    private Long communityId;
    private String title;
    private String content;
    private String tag;
    private String pics;
}
