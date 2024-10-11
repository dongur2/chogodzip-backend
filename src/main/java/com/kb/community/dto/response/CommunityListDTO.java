package com.kb.community.dto.response;

import com.kb.community.vo.Community;
import lombok.*;

import java.util.Date;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class CommunityListDTO {
    private Long communityId;
    private String tag;
    private String title;
    private Long mNo; //member
    private String nickname; //member
    private Date createdAt;
    private int views;

    //VO => DTO
    public static CommunityListDTO from(Community community) {
        return CommunityListDTO.builder()
                .communityId(community.getCommunityId())
                .tag(community.getTag())
                .title(community.getTitle())
                .mNo(community.getMember().getMno())
                .nickname(community.getMember().getName())
                .createdAt(community.getCreatedAt())
                .views(community.getViews())
                .build();
    }
}
