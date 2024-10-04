package com.kb.search.controller;

import com.kb.search.dto.SearchParam;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
@Slf4j
@Api(value = "searchController", tags = "방 지도 정보")
@PropertySource({"classpath:/application.properties"})
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/university")
    public ResponseEntity<List<University>> getAllUniversity(){

        List<University> universityList = searchService.getAllUniversity();

        return ResponseEntity.ok(universityList);

    }

    @GetMapping("/selectOne")
    public ResponseEntity<SearchParam> getOneUniversity(@RequestParam(required = false) String name){

        SearchParam searchParam = searchService.getOneUniversity(name);

        return ResponseEntity.ok(searchParam);
    }


}
