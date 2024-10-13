package com.kb.community.dto.request;

import com.kb.community.vo.CommunityCmt;
import lombok.*;

@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class CommentModifyDTO {
    private Long cmtId;
    private String content;

    //DTO => VO
    public CommunityCmt toVO() {
        return CommunityCmt.builder()
                .cmtId(cmtId)
                .content(content)
                .build();
    }
}
