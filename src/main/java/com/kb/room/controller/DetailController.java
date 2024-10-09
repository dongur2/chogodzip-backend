package com.kb.room.controller;

import com.kb.member.dto.Member;
import com.kb.room.dto.UserReview;
import com.kb.room.dto.Room;
import com.kb.room.service.RoomService;
import io.swagger.annotations.Api;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/gosiwons/{id}")
    private ResponseEntity<Room> detailGosiwons(@PathVariable Long id) {

        Room getRoom = roomService.getOneGosiwons(id);
        return ResponseEntity.ok(getRoom);
    }

    @PostMapping("/reply/{roomId}")
    private ResponseEntity<UserReview> registReivew(@PathVariable Long roomId,
                                                    @AuthenticationPrincipal Member member,
                                                    @RequestParam String reviewContent) {

        UserReview review = UserReview.builder()
                .roomId(roomId)
                .userId(member.getMno()) // Assuming `member.getMno()` returns the user's ID.
                .reviewContent(reviewContent)
                .createdAt(new Date()) // Automatically set the current date.
                .isDeleted("F") // Default to "F" (false) meaning the review is not deleted.
                .build();

        // Save the review through the service layer
        return ResponseEntity.ok(roomService.registReply(review));
    }

    @GetMapping("/review/{roomId}")
    private ResponseEntity<List<UserReview>> getReview(@PathVariable Long roomId) {
        List<UserReview> list = roomService.getAllReview(roomId);

        return ResponseEntity.ok(list);

    }


}
