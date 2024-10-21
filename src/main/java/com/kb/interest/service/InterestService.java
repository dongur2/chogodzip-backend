package com.kb.interest.service;

import com.kb.interest.dto.InterestRoom;
import com.kb.interest.mapper.InterestMapper;
import com.kb.room.dto.IsSoldOut;
import com.kb.room.vo.Room;
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
public class InterestService {

    private final InterestMapper interestMapper;


    public List<InterestRoom> getInterestRooms(Long userId) {

        return interestMapper.getUserInterestRoom(userId);
    }

    public int addInterestRoom(Long userId, Long roomId) {

       int result = interestMapper.addInterestRoom(userId,roomId);
       if(result == 1) {
           return 1;
       }
       return 0;
    }

    @Transactional
    public int deleteInterestRoom(Long userId, Long roomId) {

        int result = interestMapper.deleteInterestRoom(userId,roomId);
        if(result == 1) {
            return 1;
        }
        return 0;
    }

    public int isFavoriteRoom(Long userId, Long roomId) {
        InterestRoom result = interestMapper.isFavoriteRoom(userId, roomId);
        if(result != null) {
            return 1;
        }
        return 0;
    }


    public List<Room> myInterestRoom(Long userId) {

        List<Room> result = interestMapper.myfavoiteRoom(userId);
        return result;
    }

    public List<IsSoldOut> isSoldInfo(Long userId) {

        List<IsSoldOut> res = interestMapper.isSoldOutInfo(userId);
        return res;
    }
}
