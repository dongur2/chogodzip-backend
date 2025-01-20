package com.kb.community.vo;

import lombok.*;

import java.util.Date;

@Getter @Setter @Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class Community {
    private Long communityId;
    private Long userId;
    private String title;
    private String content;
    private String tag;
    private String pics;

    private Date createdAt;
    private Date updatedAt;

    private Integer views;
}
