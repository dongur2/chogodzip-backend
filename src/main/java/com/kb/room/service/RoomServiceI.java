package com.kb.room.service;

import com.kb.room.dto.response.detail.RoomDetailInfoDTO;
import com.kb.room.dto.response.map.OnetwoRoomMapDTO;
import com.kb.room.dto.response.map.RoomMapDTO;
import com.kb.room.dto.response.map.ShareHouseMapDTO;
import com.kb.user.dto.User;
import com.kb.room.dto.response.map.GosiwonMapDTO;
import com.kb.room.dto.request.LocationDTO;
import com.kb.room.mapper.RoomMapper;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service @Primary
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
public class RoomServiceI implements RoomService {
    private final RoomMapper roomMapper;

    @Override //지도 기반 근처 고시원 조회
    public List<GosiwonMapDTO> findNearbyGosiwons(User user, LocationDTO location) {
        List<GosiwonMapDTO> gosiwons = roomMapper.selectGosiwonsByLocation(location);
        populateRoomDetails(user, gosiwons);
        return gosiwons;
    }

    @Override //지도 기반 근처 원투룸 조회
    public List<OnetwoRoomMapDTO> findNearbyOnetwoRooms(User user, LocationDTO location) {
        List<OnetwoRoomMapDTO> rooms = roomMapper.selectOnetwoRoomsByLocation(location);
        populateRoomDetails(user, rooms);
        return rooms;
    }

    @Override //지도 기반 근처 공유주거 조회
    public List<ShareHouseMapDTO> findNearbyShareHouses(User user, LocationDTO location) {
        List<ShareHouseMapDTO> shares = roomMapper.selectShareHousesByLocation(location);
        populateRoomDetails(user, shares);
        return shares;
    }

    @Override // 매물 상세 정보 조회
    public RoomDetailInfoDTO getRoomInfo(User user, Long roomId) throws IllegalArgumentException {
        String type = Optional.ofNullable(roomMapper.selectRoomTypeByRoomId(roomId))
                .orElseThrow(IllegalArgumentException::new);

        RoomDetailInfoDTO room = switch (type) {
            case "HOUTP00001", "HOUTP00003", "HOUTP00006" -> roomMapper.selectGosiwonByRoomId(roomId);
            case "HOUTP00002", "HOUTP00004", "HOUTP00005" -> roomMapper.selectShareHouseByRoomId(roomId);
            case "HOUTP00008", "HOUTP00009" -> roomMapper.selectOnetwoRoomByRoomId(roomId);
            default -> throw new IllegalArgumentException();
        };

        //대출
        if (room.getCanLoan() != null && room.getCanLoan()) room.setLoans(selectLoans(room.getRoomId()));

        //관심설정
        if (user != null) room.setIsInterested(selectIsInterested(user.getUserId(), room.getRoomId()));
        else room.setIsInterested(false);

        //관심매물 전체 개수
        populateInterestedCnt(room);

        return room;
    }

    private <T extends RoomMapDTO> void populateRoomDetails(User user, List<T> rooms) {
        for (T room : rooms) {
            if (room.getCanLoan() != null && room.getCanLoan()) room.setLoans(selectLoans(room.getRoomId()));

            if (user != null) room.setIsInterested(selectIsInterested(user.getUserId(), room.getRoomId()));
            else room.setIsInterested(false);
        }
    }

    private List<String> selectLoans(Long roomId) {
        return roomMapper.selectLoansByRoomId(roomId);
    }

    private boolean selectIsInterested(Long userId, Long roomId) {
        return roomMapper.checkInterestedByRoomIdAndUserId(userId, roomId);
    }

    private void populateInterestedCnt(RoomDetailInfoDTO room) {
        room.setInterestCnt(roomMapper.getInterestedCountByRoomId(room.getRoomId()));
    }

    @Override @Transactional // 관심 매물 토글
    public Boolean toggleInterest(Long userId, Long roomId) {
        boolean isInterested = roomMapper.checkInterestedByRoomIdAndUserId(userId, roomId);

        if(isInterested) roomMapper.deleteInterest(userId, roomId);
        else roomMapper.insertInterest(userId, roomId);

        return !isInterested;
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
