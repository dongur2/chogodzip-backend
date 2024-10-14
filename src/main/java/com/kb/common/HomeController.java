package com.kb.common;

import com.kb.room.dto.RoomParam;
import com.kb.room.dto.response.RoomHomeDTO;
import com.kb.room.dto.response.RoomHomeMapDTO;
import com.kb.room.service.RoomTempService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@Primary
@RequiredArgsConstructor
@RequestMapping("/api/home")
@Api(value = "HomeController", tags = "홈")
@PropertySource({"classpath:/application.properties"})
public class HomeController {
    @Autowired private final RoomTempService roomService;

    @GetMapping("/rooms/area")
    public ResponseEntity<List<RoomHomeDTO>> getRoomsInArea(@RequestParam("area") String area) {
        return ResponseEntity.ok(roomService.fetchRoomsAtInterestArea(area));
    }

    @GetMapping("/rooms/map")
    public ResponseEntity<List<RoomHomeMapDTO>> getRoomsInAreaMap(RoomParam param) {
        return ResponseEntity.ok(roomService.fetchRoomsAtInterestAreaMap(param));
    }
}
