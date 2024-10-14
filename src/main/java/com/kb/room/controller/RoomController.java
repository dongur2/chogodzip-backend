package com.kb.room.controller;

import com.kb.room.dto.GosiwonRoomDTO;

import com.kb.room.dto.RoomParam;
import com.kb.room.service.RoomService;
import com.kb.room.vo.Jachi;
import com.kb.room.vo.ShareHouse;
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

    @GetMapping("/favorite")
    public ResponseEntity<Integer> getAllFavoriteRooms(@RequestParam int roomId) {
        int result = roomService.getFavoriteCnt(roomId);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/gosiwon")
    public ResponseEntity<List<GosiwonRoomDTO>> getAllGosiwons(RoomParam roomParam) {
        List<GosiwonRoomDTO> rooms = roomService.getAllGosiwons(roomParam);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/jachi")
    public ResponseEntity<List<Jachi>> getAllJachi(RoomParam roomParam) {
        List<Jachi> jachis = roomService.getAllJachis(roomParam);
        return ResponseEntity.ok(jachis);

    }

    @GetMapping("/sharehouse")
    public ResponseEntity<List<ShareHouse>> getAllSharehouse(RoomParam roomParam) {
        List<ShareHouse> sh = roomService.getAllShareHouse(roomParam);
        return ResponseEntity.ok(sh);
    }

}
