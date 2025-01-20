package com.kb.community.dto.request;

import lombok.*;

@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class CommentPostDTO {
    private Long communityId;
    private Long userId;
    private String content;
}
