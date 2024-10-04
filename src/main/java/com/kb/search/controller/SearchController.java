package com.kb.search.controller;

import com.kb.room.service.RoomService;
import com.kb.search.dto.Subway;
import com.kb.search.dto.University;
import com.kb.search.service.SearchService;
import io.swagger.annotations.Api;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
@Slf4j
@Api(value = "RoomController", tags = "방 지도 정보")
@PropertySource({"classpath:/application.properties"})
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/subway")
    public ResponseEntity<List<Subway>> getAllSubway(){

        List<Subway> subList = searchService.getAllSubway();
        return ResponseEntity.ok(subList);

    }

    @GetMapping("/university")
    public ResponseEntity<List<University>> getAllUniversity(){

        List<University> universityList = searchService.getAllUniversity();

        return ResponseEntity.ok(universityList);

    }


}
