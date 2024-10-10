package com.kb.community.service;

import com.kb.community.dto.request.CommunityPostDTO;
import com.kb.community.dto.response.CommunityDetailDTO;
import com.kb.community.dto.response.CommunityListDTO;
import com.kb.community.mapper.CommunityMapper;
import com.kb.community.vo.Community;
import com.kb.member.mapper.MemberMapper;
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
    @Autowired private MemberMapper memberMapper;

    @Override
    public List<CommunityListDTO> getAll() {
        return communityMapper.findAll().stream().map(CommunityListDTO::from).toList();
    }

    @Override
    public CommunityDetailDTO getDetail(Long id) {
        return CommunityDetailDTO.from(communityMapper.findById(id));
    }

    @Override @Transactional
    public Long add(CommunityPostDTO dto) {
        Community vo = dto.toVO();
        vo.setUser(memberMapper.selectByNo(dto.getUserId()));
        communityMapper.save(vo);
        return vo.getCommunityId();
    }
}
