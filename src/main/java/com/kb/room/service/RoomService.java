package com.kb.room.service;

import com.kb.room.dto.request.LocationDTO;
import com.kb.room.dto.response.detail.RoomDetailInfoDTO;
import com.kb.room.dto.response.map.GosiwonMapDTO;
import com.kb.room.dto.response.map.OnetwoRoomMapDTO;
import com.kb.room.dto.response.map.ShareHouseMapDTO;
import com.kb.user.dto.User;

import java.util.List;

public interface RoomService {
    List<GosiwonMapDTO> findNearbyGosiwons(User user, LocationDTO location);
    List<OnetwoRoomMapDTO> findNearbyOnetwoRooms(User user, LocationDTO location);
    List<ShareHouseMapDTO> findNearbyShareHouses(User user, LocationDTO location);

    //상세 정보 조회
    RoomDetailInfoDTO getRoomInfo(User user, Long roomId);

    //관심매물 토글
    Boolean toggleInterest(Long userId, Long roomId);
//    List<RoomTempDTO> fetchAllRooms();
//    Integer addRoom(GosiwonPostDTO dto, List<MultipartFile> pics) throws IOException; //고시원 작성
//    List<RoomHomeDTO> fetchRoomsAtInterestArea(String interestArea);
//    List<RoomHomeMapDTO> fetchRoomsAtInterestAreaMap(LocationDTO param);
}
