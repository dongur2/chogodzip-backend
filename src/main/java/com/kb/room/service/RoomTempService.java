package com.kb.room.service;

import com.kb.room.dto.request.GosiwonPostDTO;
import com.kb.room.dto.response.RoomTempDTO;

import java.util.List;

public interface RoomTempService {
    List<RoomTempDTO> fetchAllRooms();
    Long addRoom(GosiwonPostDTO dto); //고시원 작성
}
