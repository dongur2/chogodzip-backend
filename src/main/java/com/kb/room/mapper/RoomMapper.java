package com.kb.room.mapper;

import com.kb.room.dto.response.detail.RoomDetailInfoDTO;
import com.kb.room.dto.response.map.GosiwonMapDTO;
import com.kb.room.dto.request.LocationDTO;
import com.kb.room.dto.response.map.OnetwoRoomMapDTO;
import com.kb.room.dto.response.map.ShareHouseMapDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RoomMapper {
    //지도 기반
    List<String> selectLoansByRoomId(Long roomId);
    List<GosiwonMapDTO> selectGosiwonsByLocation(LocationDTO location);
    List<OnetwoRoomMapDTO> selectOnetwoRoomsByLocation(LocationDTO location);
    List<ShareHouseMapDTO> selectShareHousesByLocation(LocationDTO location);

    //매물 상세 정보 조회
    String selectRoomTypeByRoomId(Long roomId); //roomId로 매물 유형 구별 - HOUSE_TYPE_CD
    RoomDetailInfoDTO selectGosiwonByRoomId(Long roomId);
    RoomDetailInfoDTO selectShareHouseByRoomId(Long roomId);
    RoomDetailInfoDTO selectOnetwoRoomByRoomId(Long roomId);

    //관심매물 개수 카운트
    int getInterestedCountByRoomId(Long roomId);
    //관심매물 확인
    boolean checkInterestedByRoomIdAndUserId(@Param("userId") Long userId, @Param("roomId") Long roomId);
    //관심매물 등록, 삭제
    void insertInterest(@Param("userId") Long userId, @Param("roomId") Long roomId);
    void deleteInterest(@Param("userId") Long userId, @Param("roomId") Long roomId);
//
//    Gosiwon findOneGosiwon(@Param("id") Long id);
//    List<Room> findRoomByLocation(LocationDTO roomParam); //관심 지역 맵 매물 조회
//
//    int insertReply(UserReview review);
//
//    int findFavoriteCnt(int roomId);
//
//    List<UserReview> findAllReview(Long roomId);
//
//    Room findRoomByRoomId(@Param("roomId") Integer roomId); //roomId로 부동산 단일 조회
//    Gosiwon findOneByRoomId(@Param("roomId") Integer roomId); //roomId로 고시원 단일 조회
//    List<Room> findAll(); //부동산 모두 조회
//    List<Gosiwon> findLatestFourAtInterestArea(@Param("address") String address); //관심지역 최신 매물 4개 조회 //
//
//    Long saveRoom(Room room); //매물 작성
//    Long saveGosiwon(Gosiwon gosiwon); //고시원 작성
//    Long saveRoomWithLoan(RoomWithLoan roomWithLoan); //매물 & 대출 연결
//
//    GosiwonStatus calGosiwonStatus(String location);
//
//    int insertReply(@Param("userId")Long userId, @Param("roomId")Long roomId, @Param("reply") String reply);
//
//
//
//    Onetworoom findOneJachi(@Param("id") Long id);
//
//    GosiwonStatus calRoomStatus(String location);
//
//    ShareHouse findOneShare(Long id);
//
//    GosiwonStatus calShareStauts(String location);
//
//    List<Room> myRoomList(Long userId);
}
