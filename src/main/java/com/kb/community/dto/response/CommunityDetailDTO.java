package com.kb.community.dto.response;

import com.kb.community.vo.Community;
import com.kb.member.dto.Member;
import lombok.*;

import java.util.Date;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class CommunityDetailDTO {
    private Long communityId;
    private Member user;
    private String title;
    private String content;
    private String tag;
    private Date createdAt;
    private int views;

    //VO => DTO
    public static CommunityDetailDTO from(Community community) {
        return CommunityDetailDTO.builder()
                .communityId(community.getCommunityId())
                .user(community.getUser())
                .title(community.getTitle())
                .content(community.getContent())
                .tag(community.getTag())
                .createdAt(community.getCreatedAt())
                .views(community.getViews())
                .build();
    }
}
