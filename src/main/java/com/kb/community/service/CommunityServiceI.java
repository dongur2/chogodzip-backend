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

    //커뮤니티 모든 글 조회
    @Override
    public List<CommunityListDTO> getAll() {
        return communityMapper.findAll().stream().map(CommunityListDTO::from).toList();
    }

    //커뮤니티 상세글 조회
    @Override
    public CommunityDetailDTO getDetail(Long id) {
        CommunityDetailDTO dto = null;
        try {
            dto = CommunityDetailDTO.from(communityMapper.findById(id));
        } catch (Exception e) {
            log.error("상세 게시글 조회 실패: {}", e);
        }
        return dto;
    }

    //커뮤니티 작성
    @Override @Transactional
    public Long add(CommunityPostDTO dto) {
        Community vo = dto.toVO();
        try {
            vo.setMNo(memberMapper.selectById(dto.getMemberId()).getMno());
            communityMapper.save(vo);
        } catch (Exception e) {
            log.error("게시글 작성 실패 {}", e);
        }
        return vo.getCommunityId();
    }

    //커뮤니티 삭제처리
    @Override @Transactional
    public void delete(Long id) {
        try {
            communityMapper.updateIsDeletedByCommunityId(id);
        } catch (Exception e) { log.error("게시글 삭제 처리 실패 {}", e); }
    }
}
