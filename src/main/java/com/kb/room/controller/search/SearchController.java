package com.kb.room.controller.search;

import com.kb.room.dto.response.search.ResourceWithCoordinateDTO;
import com.kb.room.service.search.SearchService;
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
@RequestMapping("/api/rooms/map/search")
@Api(value = "searchController", tags = "지도 기반 대학/전철역 주변 검색")
@PropertySource({"classpath:/application.properties"})
public class SearchController {
    private final SearchService searchService;

    //모든 대학/전철 데이터 조회
    @GetMapping("/data")
    public ResponseEntity<List<ResourceWithCoordinateDTO>> getAllUnivAndSubwayWithCoordinate() {
        return ResponseEntity.ok(searchService.getAllUnivAndSubwayWithCoordinate());
    }

}
