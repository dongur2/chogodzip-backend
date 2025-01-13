package com.kb.room.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kb.room.dto.request.regist.RoomPostDTO;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

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

    @PostMapping
    public ResponseEntity<Long> createRoom(@AuthenticationPrincipal User user,
                                              @RequestPart("roomData") String roomDataJson,
                                              @RequestPart(value = "pics", required = false) List<MultipartFile> pics) throws IOException {
        if(user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        //넘어온 데이터를 DTO로 변환
        RoomPostDTO dto = convertJsonDataToDTOWithKorean(roomDataJson);

        //작성
        Long roomId = roomService.addRoom(user.getUserId(), dto, pics);
        return new ResponseEntity<>(roomId, HttpStatus.OK);
    }

    private RoomPostDTO convertJsonDataToDTOWithKorean(String jsonData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        jsonData = new String(jsonData.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        return objectMapper.readValue(jsonData, RoomPostDTO.class);
    }
}
