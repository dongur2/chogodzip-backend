package com.kb.room.mapper;

import com.kb.room.dto.response.detail.info.RoomDetailInfoDTO;
import com.kb.room.dto.response.detail.review.UserReviewDTO;
import com.kb.room.dto.response.detail.status.GuStatus;
import com.kb.room.dto.response.map.GosiwonMapDTO;
import com.kb.room.dto.request.LocationDTO;
import com.kb.room.dto.response.map.OnetwoRoomMapDTO;
import com.kb.room.dto.response.map.ShareHouseMapDTO;
import com.kb.room.vo.*;
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

    //구의 가격 데이터 계산 통계
    GuStatus calculateGosiwonPricesOfGu(String gu);
    GuStatus calculateShareHousePricesOfGu(String gu);
    GuStatus calculateOnetwoRoomPricesOfGu(String gu);

    //관심매물 개수 카운트
    int getInterestedCountByRoomId(Long roomId);
    //관심매물 확인
    boolean checkInterestedByRoomIdAndUserId(@Param("userId") Long userId, @Param("roomId") Long roomId);
    //관심매물 등록, 삭제
    void insertInterest(@Param("userId") Long userId, @Param("roomId") Long roomId);
    void deleteInterest(@Param("userId") Long userId, @Param("roomId") Long roomId);

//    List<Room> findRoomByLocation(LocationDTO roomParam); //관심 지역 맵 매물 조회

    //리뷰
    List<UserReviewDTO> selectUserReviewsOfRoom(Long roomId);
    int insertUserReview(@Param("userId")Long userId, @Param("roomId")Long roomId, @Param("content") String content);

//    List<Gosiwon> findLatestFourAtInterestArea(@Param("address") String address); //관심지역 최신 매물 4개 조회

    Long saveRoom(Room room); //매물 작성
    Long saveGosiwon(Gosiwon gosiwon); //고시원 작성
    Long saveOneTwoRoom(OnetwoRoom onetwoRoom); //원투룸 작성
    Long saveShareHouse(ShareHouse shareHouse); //공유주거 작성
    Long saveRoomWithLoan(RoomWithLoan roomWithLoan); //매물 & 대출 연결

//    List<Room> myRoomList(Long userId);
}
