package com.kb.room.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserReview {

    private Long reviewId;
    private Long roomId;
    private Long userId;
    private String reviewContent;
    private Date createdAt;
    private String isDeleted;

}
