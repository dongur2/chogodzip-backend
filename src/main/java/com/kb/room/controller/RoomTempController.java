package com.kb.room.controller;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kb.room.dto.request.GosiwonPostDTO;
import com.kb.room.dto.response.RoomTempDTO;
import com.kb.room.service.RoomTempService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Log4j
@RestController @Primary
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
@Api(value = "RoomTempController", tags = "매물 정보")
@PropertySource({"classpath:/application.properties"})
public class RoomTempController {

    private final RoomTempService roomTempService;

    //[TEST] 모든 부동산 목록 가져오기
    @GetMapping
    public ResponseEntity<List<RoomTempDTO>> getRooms() {
        List<RoomTempDTO> list = roomTempService.fetchAllRooms();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //고시원 매물 등록
    @PostMapping
    public ResponseEntity<Integer> createRoom(@RequestPart(value = "dto") String dtoJson, @RequestPart(value = "pics") List<MultipartFile> pics) throws IOException {
        //한글 처리
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);

        dtoJson = new String(dtoJson.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        GosiwonPostDTO dto = objectMapper.readValue(dtoJson, GosiwonPostDTO.class);

        Integer roomId = roomTempService.addRoom(dto, pics);
        return new ResponseEntity<>(roomId, HttpStatus.OK);
    }

}
