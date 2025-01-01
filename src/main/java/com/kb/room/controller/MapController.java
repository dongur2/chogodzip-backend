package com.kb.room.controller;

import com.kb.room.dto.response.map.GosiwonMapDTO;

import com.kb.room.dto.request.LocationDTO;
import com.kb.room.dto.response.map.OnetwoRoomMapDTO;
import com.kb.room.dto.response.map.ShareHouseMapDTO;
import com.kb.room.service.RoomService;
import com.kb.user.dto.User;
import io.swagger.annotations.Api;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final RoomService roomService;

    @GetMapping("/gosiwons")
    public ResponseEntity<List<GosiwonMapDTO>> getNearbyGosiwons(@AuthenticationPrincipal User user, LocationDTO location) {
        return ResponseEntity.ok(roomService.findNearbyGosiwons(user, location));
    }

    @GetMapping("/onetwos")
    public ResponseEntity<List<OnetwoRoomMapDTO>> getAllOnetworooms(@AuthenticationPrincipal User user, LocationDTO location) {
        return ResponseEntity.ok(roomService.findNearbyOnetwoRooms(user, location));
    }

    @GetMapping("/shares")
    public ResponseEntity<List<ShareHouseMapDTO>> getAllSharehouses(@AuthenticationPrincipal User user, LocationDTO location) {
        return ResponseEntity.ok(roomService.findNearbyShareHouses(user, location));
    }
}
