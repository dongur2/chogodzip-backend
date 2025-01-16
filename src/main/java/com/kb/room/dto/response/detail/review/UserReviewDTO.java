package com.kb.room.dto.response.detail.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class UserReviewDTO {
    private Long urvId;
    private Long roomId;
    private Long userId;
    private String pic;
    private String nickname;
    private String content;
    private Date updatedAt;
    private Date createdAt;
}
