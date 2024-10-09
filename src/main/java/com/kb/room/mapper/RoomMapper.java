package com.kb.room.mapper;

import com.kb.room.dto.Room;
import java.util.List;

import com.kb.room.dto.request.GosiwonPostDTO;
import com.kb.room.dto.request.RoomTempPostDTO;
import com.kb.room.vo.Gosiwon;
import com.kb.room.vo.RoomTemp;
import org.apache.ibatis.annotations.Param;

public interface RoomMapper {
    List<Room> findGosiwonsByLocation(@Param("lat") String lat, @Param("lng") String lng);

    List<RoomTemp> findAll();

    Long saveRoom(RoomTemp room); //매물 작성
    Long saveGosiwon(Gosiwon gosiwon);
}
