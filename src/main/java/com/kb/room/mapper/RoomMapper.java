package com.kb.room.mapper;

import com.kb.room.dto.GosiwonRoomDTO;
import com.kb.room.dto.UserReview;
import com.kb.room.dto.RoomParam;
import com.kb.room.vo.Gosiwon;
import com.kb.room.vo.GosiwonStatus;
import com.kb.room.vo.Jachi;
import com.kb.room.vo.ShareHouse;
import java.util.List;

import com.kb.room.vo.Room;
import com.kb.room.vo.RoomWithLoan;

import org.apache.ibatis.annotations.Param;


public interface RoomMapper {

    List<GosiwonRoomDTO> findGosiwonsByLocation(RoomParam roomParam);

    Gosiwon findOneGosiwon(@Param("id") Long id);

    int insertReply(UserReview review);

    int findFavoriteCnt(int roomId);

    List<UserReview> findAllReview(Long roomId);
  
    Gosiwon findOneByRoomId(@Param("roomId") Long roomId); //roomId로 부동산 단일 조회
    List<Room> findAll(); //부동산 모두 조회

    Long saveRoom(Room room); //매물 작성
    Long saveGosiwon(Gosiwon gosiwon); //고시원 작성
    Long saveRoomWithLoan(RoomWithLoan roomWithLoan); //매물 & 대출 연결

    GosiwonStatus calGosiwonStatus(String location);

    int insertReply(@Param("userId")Long userId, @Param("roomId")Long roomId, @Param("reply") String reply);

    List<Jachi> findJachiByLocation(RoomParam roomParam);

    List<ShareHouse> findShareHouseByLocation(RoomParam roomParam);
}
