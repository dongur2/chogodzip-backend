package com.kb.community.service;

import com.kb.community.dto.request.CommunityPostDTO;
import com.kb.community.dto.response.CommunityDetailDTO;
import com.kb.community.dto.response.CommunityListDTO;
import com.kb.community.mapper.CommunityMapper;
import com.kb.community.vo.Community;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j
@Service @Primary
@NoArgsConstructor @AllArgsConstructor
public class CommunityServiceI implements CommunityService {
    @Autowired private CommunityMapper communityMapper;

    //커뮤니티 모든 글 조회
    @Override
    public List<CommunityListDTO> getAll() {
        return communityMapper.findAll().stream().map(CommunityListDTO::from).toList();
    }

    //커뮤니티 상세글 조회
    @Override
    public CommunityDetailDTO getDetail(Long id) {
        return CommunityDetailDTO.from(communityMapper.findById(id));
    }

    //커뮤니티 작성
    @Override @Transactional
    public Long add(CommunityPostDTO dto) {
        Community vo = dto.toVO();
        communityMapper.save(vo);
        return vo.getCommunityId();
    }
}
