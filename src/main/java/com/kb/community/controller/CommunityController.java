package com.kb.community.controller;

import com.kb.community.dto.request.CommunityPostDTO;
import com.kb.community.dto.response.CommunityListDTO;
import com.kb.community.service.CommunityService;
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
@RequestMapping("/api/community")
@RequiredArgsConstructor
@Api(value = "CommunityController", tags = "커뮤니티")
@PropertySource({"classpath:/application.properties"})
public class CommunityController {
    private final CommunityService service;

    @GetMapping("/list")
    public ResponseEntity<List<CommunityListDTO>> selectList() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> post(@RequestBody CommunityPostDTO data) {
        Long communityId = service.add(data);
        return new ResponseEntity<>(communityId, HttpStatus.OK);
    }
}
