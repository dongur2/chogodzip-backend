package com.kb.room.service;

import com.kb.member.mapper.MemberMapper;
import com.kb.room.dto.request.GosiwonPostDTO;
import com.kb.room.dto.response.RoomTempDTO;
import com.kb.room.mapper.RoomMapper;
import com.kb.room.vo.Gosiwon;
import com.kb.room.vo.Room;
import com.kb.room.vo.RoomWithLoan;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Log4j
@Service
@Primary
@RequiredArgsConstructor
public class RoomTempServiceI implements RoomTempService{
    private final RoomMapper mapper;
    private final MemberMapper memberMapper;

    //모든 부동산 조회
    @Override
    public List<RoomTempDTO> fetchAllRooms() {
        return mapper.findAll().stream()
                .map(RoomTempDTO::from)
                .toList();
    }

    //부동산 작성 [고시원]
    @Override @Transactional
    public Integer addRoom(GosiwonPostDTO dto) {
        //ROOM
        Room roomVO = dto.toRoomVO(memberMapper.searchOneMember(dto.getWriterId()));
        mapper.saveRoom(roomVO);

        //GOSIWON
        Gosiwon gosiwonVO = dto.toGosiwonVO(roomVO);
        mapper.saveGosiwon(gosiwonVO);

        //대출 가능한 경우 부동산 & 대출 연결
        if(roomVO.getCanLoan()) {
            String[] loans = dto.getLoanInfo().getLoans().get("res").toString().split("\\|");
            for(String loan : loans) {
                mapper.saveRoomWithLoan(RoomWithLoan.builder()
                                .roomId(roomVO.getRoomId())
                                .loanId(Long.parseLong(loan))
                                .build());
            }
        }
        return roomVO.getRoomId();
    }

}
