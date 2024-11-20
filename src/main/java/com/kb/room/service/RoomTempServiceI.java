package com.kb.room.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Log4j
@Service @Primary
@RequiredArgsConstructor
public class RoomTempServiceI {
//    @Autowired private final RoomMapper roomMapper;
//    @Autowired private final UserMapper memberMapper;
//    @Autowired private final S3Uploader s3Uploader;
//
//
//    //모든 부동산 조회
//    @Override
//    public List<RoomTempDTO> fetchAllRooms() {
//        return roomMapper.findAll().stream()
//                .map(RoomTempDTO::from)
//                .toList();
//    }
//
//    //부동산 작성 [고시원]
//    @Override @Transactional
//    public Integer addRoom(GosiwonPostDTO dto, List<MultipartFile> pics) throws IOException {
//        //사진 업로드
//        List<String> picUrls = s3Uploader.uploadList(pics.toArray(new MultipartFile[pics.size()]));
//
//        //ROOM
//        Room roomVO = dto.toRoomVO(1L, picUrls);
////        Room roomVO = dto.toRoomVO(memberMapper.searchOneMember(dto.getWriterId()), picUrls);
//        roomMapper.saveRoom(roomVO);
//
//        //GOSIWON
//        Gosiwon gosiwonVO = dto.toGosiwonVO(roomVO);
//        roomMapper.saveGosiwon(gosiwonVO);
//
//        //대출 가능한 경우 부동산 & 대출 연결
//        if(roomVO.getCanLoan()) {
//            String[] loans = dto.getLoanInfo().getLoans().get("res").toString().split("\\|");
//            for(String loan : loans) {
//                roomMapper.saveRoomWithLoan(RoomWithLoan.builder()
//                                .roomId(roomVO.getRoomId())
//                                .loanId(Long.parseLong(loan))
//                                .build());
//            }
//        }
//        return roomVO.getRoomId();
//    }
//
//    //메인: 관심 지역 매물 조회
//    @Override
//    public List<RoomHomeDTO> fetchRoomsAtInterestArea(String interestArea) {
//        List<Gosiwon> rooms = roomMapper.findLatestFourAtInterestArea(interestArea);
//        return rooms.stream().map(RoomHomeDTO::from).toList();
//    }
//
//    @Override
//    public List<RoomHomeMapDTO> fetchRoomsAtInterestAreaMap(LocationDTO param) {
//        List<Room> rooms = roomMapper.findRoomByLocation(param);
//        return rooms.stream().map(RoomHomeMapDTO::from).toList();
//    }
}
