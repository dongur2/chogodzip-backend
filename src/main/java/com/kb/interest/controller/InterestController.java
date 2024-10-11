package com.kb.interest.controller;

import com.kb.interest.dto.InterestRoom;
import com.kb.interest.service.InterestService;
import com.kb.member.service.MemberService;
import io.swagger.annotations.Api;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    private final MemberService memberService;

    @GetMapping("")
    public ResponseEntity<List<InterestRoom>> getInterestRooms(String userName) {
        Long userId = memberService.searchOneMember(userName);
        List<InterestRoom> list = interestService.getInterestRooms(userId);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/add")
    public ResponseEntity<Integer> addInterestRoom(String userName, Long roomId) {
        Long userId = memberService.searchOneMember(userName);
        int interestRoom = interestService.addInterestRoom(userId, roomId);
        return ResponseEntity.ok(interestRoom);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Integer> deleteInterestRoom(String userName, Long roomId) {

        Long userId = memberService.searchOneMember(userName);
        int deleteInterest = interestService.deleteInterestRoom(userId,roomId);
        return ResponseEntity.ok(deleteInterest);
    }

}
