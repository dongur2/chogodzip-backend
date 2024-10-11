package com.kb.review.vo;

import lombok.*;

import java.util.Date;

@Getter
@Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class UserReview {
    private Long reviewId;
    private Long roomId;
    private Long userId;
    private String reviewContent;
    private Date createdAt;
    private String isDeleted;
}
