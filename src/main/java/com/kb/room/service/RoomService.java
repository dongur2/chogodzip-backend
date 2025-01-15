package com.kb.room.service;

import com.kb.room.dto.request.LocationDTO;
import com.kb.room.dto.request.regist.RoomPostDTO;
import com.kb.room.dto.response.detail.RoomDetailInfoDTO;
import com.kb.room.dto.response.detail.status.GuStatus;
import com.kb.room.dto.response.map.GosiwonMapDTO;
import com.kb.room.dto.response.map.OnetwoRoomMapDTO;
import com.kb.room.dto.response.map.ShareHouseMapDTO;
import com.kb.user.dto.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RoomService {
    List<GosiwonMapDTO> findNearbyGosiwons(User user, LocationDTO location);
    List<OnetwoRoomMapDTO> findNearbyOnetwoRooms(User user, LocationDTO location);
    List<ShareHouseMapDTO> findNearbyShareHouses(User user, LocationDTO location);

    //상세 정보 조회
    RoomDetailInfoDTO getRoomInfo(User user, Long roomId);
    GuStatus calculateGuStatus(String typeCode, String gu); //해당 구의 가격 데이터 조회

    //관심매물 토글
    Boolean toggleInterest(Long userId, Long roomId);
//    List<RoomTempDTO> fetchAllRooms();

    //매물 작성
    Long addRoom(Long writerId, RoomPostDTO dto, List<MultipartFile> pics) throws IOException; //고시원 작성

//    List<RoomHomeDTO> fetchRoomsAtInterestArea(String interestArea);
//    List<RoomHomeMapDTO> fetchRoomsAtInterestAreaMap(LocationDTO param);
}
