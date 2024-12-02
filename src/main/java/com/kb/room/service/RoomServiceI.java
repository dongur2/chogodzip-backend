package com.kb.room.service;

import com.kb.room.dto.response.map.OnetwoRoomMapDTO;
import com.kb.room.dto.response.map.ShareHouseMapDTO;
import com.kb.user.mapper.UserMapper;
import com.kb.room.dto.response.map.GosiwonMapDTO;
import com.kb.room.dto.request.LocationDTO;
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
public class RoomServiceI implements RoomService {
    private final RoomMapper roomMapper;
    private final UserMapper memberMapper;

    //지도 기반 근처 고시원 조회
    public List<GosiwonMapDTO> findNearbyGosiwons(LocationDTO roomParam) {
        List<GosiwonMapDTO> gosiwons = roomMapper.selectGosiwonsByLocation(roomParam);

        //대출 가능한 매물일 경우 가능한 대출 목록 추가
        for(GosiwonMapDTO gosiwon : gosiwons) {
            if(gosiwon.getCanLoan() != null && gosiwon.getCanLoan()) {
                gosiwon.setLoans(roomMapper.selectLoansByRoomId(gosiwon.getRoomId()));
            }
        }
        return gosiwons;
    }

    //지도 기반 근처 원투룸 조회
    public List<OnetwoRoomMapDTO> findNearbyOnetwoRooms(LocationDTO roomParam) {
        List<OnetwoRoomMapDTO> rooms = roomMapper.selectOnetwoRoomsByLocation(roomParam);

        for(OnetwoRoomMapDTO room : rooms) {
            if(room.getCanLoan() != null && room.getCanLoan()) {
                room.setLoans(roomMapper.selectLoansByRoomId(room.getRoomId()));
            }
        }
        return rooms;
    }

    //지도 기반 근처 공유주거 조회
    public List<ShareHouseMapDTO> findNearbyShareHouses(LocationDTO roomParam) {
        List<ShareHouseMapDTO> shares = roomMapper.selectShareHousesByLocation(roomParam);

        for(ShareHouseMapDTO share : shares) {
            if(share.getCanLoan() != null && share.getCanLoan()) {
                share.setLoans(roomMapper.selectLoansByRoomId(share.getRoomId()));
            }
        }
        return shares;
    }

//    @Transactional(rollbackFor = Exception.class)
//    public int registReply(Long userId, Long roomId, String reply) {
//
//        int result = roomMapper.insertReply(userId, roomId, reply);
//        return result;
//    }
//
//
//    public Gosiwon getOneGosiwons(Long id) {
//        return roomMapper.findOneGosiwon(id);
//    }
//
//    public int getFavoriteCnt(int roomId) {
//        return roomMapper.findFavoriteCnt(roomId);
//    }
//
//    public List<UserReview> getAllReview(Long roomId) {
//        List<UserReview> reviewList = roomMapper.findAllReview(roomId);
//        reviewList.forEach(review -> {
////            review.setUserPic(memberMapper.findPicOfMember(review.getUserId()));
////            review.setUserName(memberMapper.selectByNo(review.getUserId()).getUsername());
//        });
//        return reviewList;
//    }
//
//    public GosiwonStatus calStatus(String location) {
//        return roomMapper.calGosiwonStatus(location);
//    }
//
//
//    public Onetworoom getOneJachis(Long id) {
//        return roomMapper.findOneJachi(id);
//    }
//
//    public GosiwonStatus calRoomStatus(String location) {
//        return roomMapper.calRoomStatus(location);
//    }
//
//    public ShareHouse getOneShare(Long id) {
//        return roomMapper.findOneShare(id);
//    }
//
//    public GosiwonStatus calShareStauts(String location) {
//
//        return roomMapper.calShareStauts(location);
//    }
//
//    public List<Room> myRoomList(Long userId) {
//        return roomMapper.myRoomList(userId);
//    }
}
