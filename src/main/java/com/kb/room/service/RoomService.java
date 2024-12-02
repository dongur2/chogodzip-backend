package com.kb.room.service;

import com.kb.room.dto.request.LocationDTO;
import com.kb.room.dto.response.map.GosiwonMapDTO;
import com.kb.room.dto.response.map.OnetwoRoomMapDTO;
import com.kb.room.dto.response.map.ShareHouseMapDTO;

import java.util.List;

public interface RoomService {
    List<GosiwonMapDTO> findNearbyGosiwons(LocationDTO roomParam);
    List<OnetwoRoomMapDTO> findNearbyOnetwoRooms(LocationDTO roomParam);
    List<ShareHouseMapDTO> findNearbyShareHouses(LocationDTO roomParam);
//    List<RoomTempDTO> fetchAllRooms();
//    Integer addRoom(GosiwonPostDTO dto, List<MultipartFile> pics) throws IOException; //고시원 작성
//    List<RoomHomeDTO> fetchRoomsAtInterestArea(String interestArea);
//    List<RoomHomeMapDTO> fetchRoomsAtInterestAreaMap(LocationDTO param);
}
