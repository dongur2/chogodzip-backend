package com.kb.room.controller;

import com.kb.room.dto.Room;
import com.kb.room.service.RoomService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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


}
