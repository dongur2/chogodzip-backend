package com.kb.community.vo;

import com.kb.member.dto.Member;
import lombok.*;

import java.util.Date;

@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class Community {
    private Long communityId;
    private Member user;
    private String title;
    private String content;
    private String tag;

    private Date createdAt;
    private Date updatedAt;

    private Boolean isDeleted;
    private Integer views;
}
