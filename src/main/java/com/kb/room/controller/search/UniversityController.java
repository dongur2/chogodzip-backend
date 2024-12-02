package com.kb.room.controller.search;

import com.kb.room.dto.response.search.CoordinateDTO;
import com.kb.room.vo.search.University;
import com.kb.room.service.search.UniversityService;
import io.swagger.annotations.Api;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/universities")
@Api(value = "universityController", tags = "대학 정보")
@PropertySource({"classpath:/application.properties"})
public class UniversityController {
    private final UniversityService univService;

    //모든 대학 데이터 조회
    @GetMapping
    public ResponseEntity<List<University>> getAllUniversities(){
        return ResponseEntity.ok(univService.getAllUniversities());
    }

    //대학의 좌표 검색
    @GetMapping("/search")
    public ResponseEntity<CoordinateDTO> getCoordinatesByUniversityName(@RequestParam(required = false) String name){
        return ResponseEntity.ok(univService.findCoordinatesByUniversityName(name));
    }
}
