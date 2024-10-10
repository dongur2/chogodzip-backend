package com.kb.room.mapper;

import com.kb.room.dto.UserReview;
import com.kb.room.dto.Room;
import com.kb.room.dto.RoomParam;
import java.util.List;

import com.kb.room.dto.request.GosiwonPostDTO;
import com.kb.room.dto.request.RoomTempPostDTO;
import com.kb.room.vo.Gosiwon;
import com.kb.room.vo.RoomTemp;
import com.kb.room.vo.RoomWithLoan;

import org.apache.ibatis.annotations.Param;


public interface RoomMapper {
    List<Room> findGosiwonsByLocation(@Param("lat") String lat, @Param("lng") String lng);

    List<Room> findGosiwonsByLocation(RoomParam roomParam);

    Room findOneGosiwon(@Param("id") Long id);

    int insertReply(UserReview review);

    int findFavoriteCnt(int roomId);

    List<UserReview> findAllReview(Long roomId);
  
    Gosiwon findOneByRoomId(@Param("roomId") Long roomId); //roomId로 부동산 단일 조회
    List<RoomTemp> findAll(); //부동산 모두 조회

    Long saveRoom(RoomTemp room); //매물 작성
    Long saveGosiwon(Gosiwon gosiwon); //고시원 작성
    Long saveRoomWithLoan(RoomWithLoan roomWithLoan); //매물 & 대출 연결
}
