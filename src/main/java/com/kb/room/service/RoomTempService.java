package com.kb.room.service;

import com.kb.room.dto.response.RoomTempDTO;

import java.util.List;

public interface RoomTempService {
    List<RoomTempDTO> fetchAllRooms();
}
