package com.kb.review.service;

import com.kb.review.mapper.ReviewMapper;
import com.kb.review.vo.UserReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewMapper reviewMapper;

    public List<UserReview> getAllReview(long roomId) {

        return reviewMapper.findAllByRoomId(roomId);
    }
}
