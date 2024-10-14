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

    @Override
    public List<RoomTempDTO> fetchAllRooms() {
        return mapper.findAll().stream()
                .map(RoomTempDTO::from)
                .toList();
    }

    @Override @Transactional
    public Long addRoom(GosiwonPostDTO dto) {
        //부동산 작성
        Room roomVO = dto.toRoomVO(memberMapper.selectById(dto.getWriterId()).getMno());
        mapper.saveRoom(roomVO);
        
        //고시원 작성
        Gosiwon gosiwonVO = dto.toGosiwonVO(roomVO);

        mapper.saveGosiwon(gosiwonVO);

        //대출 가능한 경우 부동산 & 대출 연결
        if(roomVO.getCanLoan()) {
            String[] loans = dto.getLoanInfo().getLoans().get("res").toString().split("\\|");
            for(String loan : loans) {
                mapper.saveRoomWithLoan(RoomWithLoan.builder()
                                .loanId(Long.parseLong(loan))
                                .build());
            }
        }

//        return roomVO.getRoomId();
        return 1L;
    }


}
