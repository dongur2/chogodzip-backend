package com.kb.community.dto.response;

import lombok.*;

import java.util.Date;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class CommentDetailDTO {
    private Long cmtId;
    private Long userId;
    private String nickname;
    private String pic;
    private String content;
    private Date createdAt;
}
