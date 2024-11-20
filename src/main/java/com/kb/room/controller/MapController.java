package com.kb.room.controller;

import com.kb.room.dto.response.GosiwonMapDTO;

import com.kb.room.dto.request.LocationDTO;
import com.kb.room.dto.response.OnetwoRoomMapDTO;
import com.kb.room.dto.response.ShareHouseMapDTO;
import com.kb.room.service.RoomServiceI;
import io.swagger.annotations.Api;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms/map")
@Api(value = "MapController", tags = "지도 기반 매물 정보 조회")
@PropertySource({"classpath:/application.properties"})
public class MapController {
    private final RoomServiceI roomService;

    @GetMapping("/gosiwons")
    public ResponseEntity<List<GosiwonMapDTO>> getNearbyGosiwons(LocationDTO roomParam) {
        return ResponseEntity.ok(roomService.findNearbyGosiwons(roomParam));
    }

    @GetMapping("/onetwos")
    public ResponseEntity<List<OnetwoRoomMapDTO>> getAllOnetworooms(LocationDTO roomParam) {
        return ResponseEntity.ok(roomService.findNearbyOnetwoRooms(roomParam));
    }

    @GetMapping("/shares")
    public ResponseEntity<List<ShareHouseMapDTO>> getAllSharehouses(LocationDTO roomParam) {
        return ResponseEntity.ok(roomService.findNearbyShareHouses(roomParam));
    }

//    @GetMapping("/favorite")
//    public ResponseEntity<Integer> getAllFavoriteRooms(@RequestParam int roomId) {
//        int result = roomService.getFavoriteCnt(roomId);
//        return ResponseEntity.ok(result);

//    }


}
