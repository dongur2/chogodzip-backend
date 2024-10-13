package com.kb.community.controller;

import com.kb.community.dto.request.CommunityModifyDTO;
import com.kb.community.dto.request.CommunityPostDTO;
import com.kb.community.dto.response.CommunityDetailDTO;
import com.kb.community.dto.response.CommunityListDTO;
import com.kb.community.service.CommunityService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.annotations.Update;
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
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Long> post(@RequestBody CommunityPostDTO data) {
        return ResponseEntity.ok(service.add(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunityDetailDTO> selectOne(@PathVariable("id") Long communityId) {
        return ResponseEntity.ok(service.getDetail(communityId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Long> update(@RequestBody CommunityModifyDTO dto) {
        return ResponseEntity.ok(service.modifyPostContent(dto));
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") Long id) {
        service.delete(id);
        return HttpStatus.OK;
    }
}
