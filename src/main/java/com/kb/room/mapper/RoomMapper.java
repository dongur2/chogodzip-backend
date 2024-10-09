package com.kb.room.mapper;

import com.kb.room.dto.Room;
import java.util.List;

import com.kb.room.vo.RoomTemp;
import org.apache.ibatis.annotations.Param;

public interface RoomMapper {
    List<Room> findGosiwonsByLocation(@Param("lat") String lat, @Param("lng") String lng);

    List<RoomTemp> findAll();
}
