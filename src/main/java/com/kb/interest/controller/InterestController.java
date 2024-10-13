package com.kb.interest.controller;

import com.kb.interest.dto.InterestRoom;
import com.kb.interest.service.InterestService;
import com.kb.member.service.MemberService;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interest")
@RequiredArgsConstructor
@Slf4j
@Api(value = "interestController", tags = "관심 매물 상세 정보")
@PropertySource({"classpath:/application.properties"})
public class InterestController {

    private final InterestService interestService;
    private final MemberService memberService;//멤버 조회

    @GetMapping("")
    public ResponseEntity<List<InterestRoom>> getInterestRooms(String userName) {
        Long userId = memberService.searchOneMember(userName);
        List<InterestRoom> list = interestService.getInterestRooms(userId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/isFavorite")
    public ResponseEntity<Integer> getInterestRoomsFavorite(String userName, Long roomId) {
        Long userId = memberService.searchOneMember(userName);
        int result = interestService.isFavoriteRoom(userId,roomId);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<Integer> addInterestRoom(String userName, Long roomId) {
        Long userId = memberService.searchOneMember(userName);
        int interestRoom = interestService.addInterestRoom(userId, roomId);
        return ResponseEntity.ok(interestRoom);
    }

    @GetMapping("/isOwn")
    public ResponseEntity<Long> getIsOwn(String userName) {
        Long userId = memberService.searchOneMember(userName);

        return ResponseEntity.ok(userId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Integer> deleteInterestRoom(@RequestBody Map<String, String> params) {
        System.out.println("@@@@");
        System.out.println(params);

        Long userId = memberService.searchOneMember(""+params.get("userName"));
        System.out.println("ididdidd "+userId);
        int deleteInterest = interestService.deleteInterestRoom(userId, Long.parseLong(""+params.get("roomId")));
        return ResponseEntity.ok(deleteInterest);
    }

}
