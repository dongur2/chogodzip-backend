package com.kb.room.service;

import com.kb.room.dto.request.GosiwonPostDTO;
import com.kb.room.dto.response.RoomTempDTO;
import com.kb.room.mapper.RoomMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
@Primary
@RequiredArgsConstructor
public class RoomTempServiceI implements RoomTempService{
    private final RoomMapper mapper;

    @Override
    public List<RoomTempDTO> fetchAllRooms() {
        return mapper.findAll().stream()
                .map(RoomTempDTO::from)
                .toList();
    }

    @Override
    public Long addRoom(GosiwonPostDTO dto) {
        return 0L;
    }


}
