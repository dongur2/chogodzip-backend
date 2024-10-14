package com.kb.room.service;

import com.kb.room.dto.request.GosiwonPostDTO;
import com.kb.room.dto.response.RoomTempDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RoomTempService {
    List<RoomTempDTO> fetchAllRooms();
    Integer addRoom(GosiwonPostDTO dto, List<MultipartFile> pics) throws IOException; //고시원 작성
}
