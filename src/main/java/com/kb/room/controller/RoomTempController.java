package com.kb.room.controller;

import com.kb.room.dto.request.GosiwonPostDTO;
import com.kb.room.dto.response.RoomTempDTO;
import com.kb.room.service.RoomTempService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
@Api(value = "RoomTempController", tags = "매물 정보")
@PropertySource({"classpath:/application.properties"})
public class RoomTempController {
    private final RoomTempService service;

    //[TEST] 모든 부동산 목록 가져오기
    @GetMapping
    public ResponseEntity<List<RoomTempDTO>> getRooms() {
        List<RoomTempDTO> list = service.fetchAllRooms();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //[TEMP] 고시원 매물 등록
    @PostMapping
    public ResponseEntity<Long> createRoom(@RequestBody GosiwonPostDTO dto) {
        Long roomId = service.addRoom(dto);
        return new ResponseEntity<>(roomId, HttpStatus.OK);
    }
}
