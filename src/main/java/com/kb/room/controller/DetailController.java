package com.kb.room.controller;

import com.kb.member.dto.Member;
import com.kb.member.service.MemberService;
import com.kb.room.dto.UserReview;
import com.kb.room.service.RoomService;
import com.kb.room.vo.Gosiwon;
import com.kb.room.vo.GosiwonStatus;
import com.kb.room.vo.Jachi;
import com.kb.room.vo.Room;
import com.kb.room.vo.ShareHouse;
import io.swagger.annotations.Api;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/detail")
@RequiredArgsConstructor
@Slf4j
@Api(value = "DetailController", tags = "방 상세 정보")
@PropertySource({"classpath:/application.properties"})
public class DetailController {

    private final RoomService roomService;
    private final MemberService memberService;

    @GetMapping("/gosiwons/{id}")
    private ResponseEntity<Gosiwon> detailGosiwons(@PathVariable Long id) {

        Gosiwon getRoom = roomService.getOneGosiwons(id);
        return ResponseEntity.ok(getRoom);
    }
    @GetMapping("/room/{id}")
    private ResponseEntity<Jachi> detailJachi(@PathVariable Long id){
        Jachi getJachi = roomService.getOneJachis(id);
        return ResponseEntity.ok(getJachi);
    }


    @GetMapping("/sharehouse/{id}")
    public ResponseEntity<ShareHouse> detailShare(@PathVariable Long id){
        ShareHouse getShare = roomService.getOneShare(id);
        return ResponseEntity.ok(getShare);
    }

    @PostMapping("/regist")
    public ResponseEntity<Integer> detailRegist(String userName, Long roomId, String reply) {
        Long userId = memberService.searchOneMember(userName);
        int result = roomService.registReply(userId, roomId, reply);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/review/{roomId}")
    private ResponseEntity<List<UserReview>> getReview(@PathVariable Long roomId) {
        List<UserReview> list = roomService.getAllReview(roomId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/status")
    private ResponseEntity<GosiwonStatus> getStatus(String location) {

        GosiwonStatus result  = roomService.calStatus(location);
        System.out.println(location+" dfadfasdfasdf " + result);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/roomStatus")
    private ResponseEntity<GosiwonStatus> getRoomStatus(String location){
        GosiwonStatus result =roomService.calRoomStatus(location);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/shareStatus")
    private ResponseEntity<GosiwonStatus> getShareStatus(String location){
        GosiwonStatus result = roomService.calShareStauts(location);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/myRegist")
    private ResponseEntity<List<Room>> getMyRegist(String userName) {
        Long userId = memberService.searchOneMember(userName);
        List<Room> result = roomService.myRoomList(userId);
        return ResponseEntity.ok(result);

    }



}
