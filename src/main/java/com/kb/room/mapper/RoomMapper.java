package com.kb.room.mapper;

import com.kb.room.dto.Room;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomMapper {


    List<Room> findGosiwonsByLocation(@Param("lat") String lat, @Param("lng") String lng);
}
