package com.kb.community.vo;

import lombok.*;

import java.util.Date;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Article {
    private Long articleId;
    private String title;
    private String content;
    private String thumbnail;
    private Date createdAt;
}
