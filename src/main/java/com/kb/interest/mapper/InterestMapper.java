package com.kb.interest.mapper;

import com.kb.interest.dto.InterestRoom;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InterestMapper {

    List<InterestRoom> getUserInterestRoom(Long userId);

    int addInterestRoom(@Param("userId") Long userId, @Param("roomId") Long roomId);

    int deleteInterestRoom(@Param("userId") Long userId, @Param("roomId") Long roomId);
}
