package com.kb.room.controller;

import com.kb.room.dto.Room;
import com.kb.room.dto.RoomParam;
import com.kb.room.service.RoomService;
import io.swagger.annotations.Api;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/map")
@RequiredArgsConstructor
@Slf4j
@Api(value = "RoomController", tags = "방 지도 정보")
@PropertySource({"classpath:/application.properties"})
public class RoomController {

    private final RoomService roomService;


    @GetMapping("/gosiwon")
    public ResponseEntity<List<Room>> getAllAvailableRooms(@RequestParam String lat,
                                                           @RequestParam String lng) {
        RoomParam roomParam = new RoomParam();
        roomParam.setLat(lat);
        roomParam.setLng(lng);

        List<Room> rooms = roomService.getAllGosiwons(roomParam);
        return ResponseEntity.ok(rooms);
    }

}
