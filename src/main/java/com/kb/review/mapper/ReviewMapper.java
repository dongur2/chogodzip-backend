package com.kb.review.mapper;

import com.kb.review.vo.UserReview;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReviewMapper {
    List<UserReview> findAll();
    List<UserReview> findAllByRoomId(@Param("roomId") Long roomId); // DB에서 모든 리뷰를 가져오는 애 근데 그 글에 대한 리뷰잖아 리뷰랑 그글이랑 공유하는 FK 방 번호를 가지고 방 번호에 딸린 모든 리뷰를 가져온다.
}