package com.kb.community.dto.response;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class CommunityDetailDTO {
    private Long communityId;
    private Long userId;
    private String nickname;
    private String userPic;
    private String title;
    private String content;
    private String tag;
    private Date createdAt;
    private int views;

    private List<CommentDetailDTO> comments;
}
