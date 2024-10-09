package com.kb.room.mapper;

import com.kb.room.dto.UserReview;
import com.kb.room.dto.Room;
import com.kb.room.dto.RoomParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomMapper {

    List<Room> findGosiwonsByLocation(RoomParam roomParam);

    Room findOneGosiwon(@Param("id") Long id);

    int insertReply(UserReview review);

    int findFavoriteCnt(int roomId);

    List<UserReview> findAllReview(Long roomId);
}
