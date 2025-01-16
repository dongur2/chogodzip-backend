package com.kb.room.vo.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class UserReview {
    private Long urvId;
    private Long roomId;
    private Long userId;
    private String content;
    private Date updatedAt;
    private Date createdAt;
    private Boolean isDeleted;
}
