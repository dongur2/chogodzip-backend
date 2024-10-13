package com.kb.room.service;

import com.kb.room.dto.GosiwonRoomDTO;
import com.kb.room.dto.UserReview;
import com.kb.room.dto.RoomParam;
import com.kb.room.mapper.RoomMapper;
import com.kb.room.vo.Gosiwon;
import com.kb.room.vo.GosiwonStatus;
import com.kb.room.vo.Jachi;
import com.kb.room.vo.ShareHouse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j
@Service
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
public class RoomService {

    private final RoomMapper roomMapper;

    @Transactional(rollbackFor = Exception.class)
    public int registReply(Long userId, Long roomId, String reply) {

        int result = roomMapper.insertReply(userId, roomId, reply);
        return result;
    }




    public List<GosiwonRoomDTO> getAllGosiwons(RoomParam roomParam) {

        return roomMapper.findGosiwonsByLocation(roomParam);
    }


    public Gosiwon getOneGosiwons(Long id) {
        return roomMapper.findOneGosiwon(id);
    }

    public int getFavoriteCnt(int roomId) {
        return roomMapper.findFavoriteCnt(roomId);
    }

    public List<UserReview> getAllReview(Long roomId) {

        return roomMapper.findAllReview(roomId);
    }

    public GosiwonStatus calStatus(String location) {
        return roomMapper.calGosiwonStatus(location);
    }

    public List<Jachi> getAllJachis(RoomParam roomParam) {
        return roomMapper.findJachiByLocation(roomParam);
    }

    public List<ShareHouse> getAllShareHouse(RoomParam roomParam) {

        return roomMapper.findShareHouseByLocation(roomParam);
    }
}
