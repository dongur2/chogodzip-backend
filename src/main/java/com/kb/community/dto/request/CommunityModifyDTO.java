package com.kb.community.dto.request;

import com.kb.community.vo.Community;
import lombok.*;

@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class CommunityModifyDTO {
    private Long communityId;
    private String title;
    private String content;
    private String tag;
    private String pics;

    //DTO => VO
    public Community toVO() {
        return Community.builder()
                .communityId(communityId)
                .title(title)
                .content(content)
                .tag(tag)
                .pics(pics)
                .build();
    }
}
