package com.kb.community.vo;

import lombok.*;

import java.util.Date;

@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class CommunityCmt {
    private Long cmtId;
    private Long communityId;
    private Long userId;
    private String content;
    private Date createdAt;
    private Date updatedAt;
}
