package com.kb.room.controller;

import com.kb.room.service.RoomService;
import com.kb.user.dto.User;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController @Primary
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
@Api(value = "RoomController", tags = "매물 정보")
@PropertySource({"classpath:/application.properties"})
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/{roomId}")
    public ResponseEntity<?> getRoomDetails(@AuthenticationPrincipal User user, @PathVariable("roomId") String roomId) {
        try {
            return ResponseEntity.ok(roomService.getRoomInfo(user, Long.parseLong(roomId)));
        } catch (IllegalArgumentException e) {
            log.error("roomId : {} 에 해당하는 데이터가 없습니다. {}", roomId, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/{roomId}/interest")
    public ResponseEntity<Boolean> toggleInterest(@AuthenticationPrincipal User user, @PathVariable("roomId") Long roomId) {
        if(user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Boolean isInterested = roomService.toggleInterest(user.getUserId(), roomId);
        return ResponseEntity.ok(isInterested);
    }



//    @GetMapping("/{roomId}/interest")
//    public ResponseEntity<Integer> getAllFavoriteRooms(@PathVariable("roomId") Long roomId) {
//        int result = roomService.getFavoriteCnt(roomId);
//        return ResponseEntity.ok(result);
//    }

//    //고시원 매물 등록
//    @PostMapping
//    public ResponseEntity<Integer> createRoom(@RequestPart(value = "dto") String dtoJson, @RequestPart(value = "pics") List<MultipartFile> pics) throws IOException {
//        //한글 처리
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
//        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//        objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
//
//        dtoJson = new String(dtoJson.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//        GosiwonPostDTO dto = objectMapper.readValue(dtoJson, GosiwonPostDTO.class);
//
//        Integer roomId = roomTempService.addRoom(dto, pics);
//        return new ResponseEntity<>(roomId, HttpStatus.OK);
//    }

}
