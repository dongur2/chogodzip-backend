package com.kb.room.service;

import com.kb.room.dto.Room;
import com.kb.room.dto.RoomParam;
import com.kb.room.mapper.RoomMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Log4j
@Service
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
public class RoomService {

    private final RoomMapper roomMapper;

    public List<Room> getAllGosiwons(RoomParam roomParam) {

        return roomMapper.findGosiwonsByLocation(roomParam.getLat(), roomParam.getLng());
    }


}
