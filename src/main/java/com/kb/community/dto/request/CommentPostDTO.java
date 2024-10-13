package com.kb.community.dto.request;

import com.kb.community.vo.CommunityCmt;
import lombok.*;

@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class CommentPostDTO {
    private Long communityId;
    private String memberId;
    private String content;

    //DTO => VO
    public CommunityCmt toVO() {
        return CommunityCmt.builder()
                .communityId(communityId)
                .memberId(memberId)
                .content(content)
                .build();
    }
}
