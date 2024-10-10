package com.kb.room.service;

import com.kb.room.dto.GosiwonRoomDTO;
import com.kb.room.dto.UserReview;
import com.kb.room.dto.Room;
import com.kb.room.dto.RoomParam;
import com.kb.room.mapper.RoomMapper;
import java.util.List;
import java.util.NoSuchElementException;
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
    public UserReview registReply(UserReview review) {
        int result = roomMapper.insertReply(review);

        // Check if the insertion was successful
        if(result != 1) {
            throw new NoSuchElementException("Failed to insert review");
        }

        return review; // Return the review object after successful insertion
    }




    public List<GosiwonRoomDTO> getAllGosiwons(RoomParam roomParam) {

        return roomMapper.findGosiwonsByLocation(roomParam);
    }


    public Room getOneGosiwons(Long id) {
        return roomMapper.findOneGosiwon(id);
    }

    public int getFavoriteCnt(int roomId) {
        return roomMapper.findFavoriteCnt(roomId);
    }

    public List<UserReview> getAllReview(Long roomId) {

        return roomMapper.findAllReview(roomId);
    }
}
