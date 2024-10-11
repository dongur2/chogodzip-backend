package com.kb.community.vo;

import lombok.*;

import java.util.Date;

@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class Community {
    private Long communityId;
    private Long mNo;
    private String memberName; //DB 미저장
    private String title;
    private String content;
    private String tag;

    private Date createdAt;
    private Date updatedAt;

    private Boolean isDeleted;
    private Integer views;
}
