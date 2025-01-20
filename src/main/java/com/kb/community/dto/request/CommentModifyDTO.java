package com.kb.community.dto.request;

import lombok.*;

@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class CommentModifyDTO {
    private Long cmtId;
    private String content;
}
