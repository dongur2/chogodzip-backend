package com.kb.community.vo;

import lombok.*;

import java.util.Date;

@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class CommunityCmt {
    private Long cmtId;

    private Long communityId;

    private Long mNo;
    private String memberId; // DB 미저장
    private String memberName; // DB 미저장

    private String content;

    private Date createdAt;
    private Date updatedAt;

    private Boolean isDeleted;
}
