package com.kb.community.service;

import com.kb.community.dto.request.CommunityModifyDTO;
import com.kb.community.dto.request.CommunityPostDTO;
import com.kb.community.dto.response.CommentDetailDTO;
import com.kb.community.dto.response.CommunityDetailDTO;
import com.kb.community.dto.response.CommunityListDTO;
import com.kb.community.mapper.CommunityCmtMapper;
import com.kb.community.mapper.CommunityMapper;
import com.kb.community.vo.Community;
import com.kb.community.vo.CommunityCmt;
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
    @Autowired private CommunityCmtMapper cmtMapper;

    //커뮤니티 모든 글 조회
    @Override
    public List<CommunityListDTO> getAll() {
        return communityMapper.findAll().stream().map(CommunityListDTO::from).toList();
    }

    //커뮤니티 상세글 조회
    @Override @Transactional
    public CommunityDetailDTO getDetail(Long id) {
        CommunityDetailDTO dto = null;
        try {
            dto = CommunityDetailDTO.from(communityMapper.findById(id));

            //댓글
            List<CommunityCmt> cmts = cmtMapper.getAllByCommunityId(id);
            if(cmts != null) dto.setComments(cmts.stream().map(CommentDetailDTO::from).toList());

            //조회하면서 조회수 카운트
            Integer hits = getHits(id, dto.getViews());
            dto.setViews(hits);

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

    //커뮤니티 수정
    @Override @Transactional
    public Long modifyPostContent(CommunityModifyDTO dto) {
       try {
           communityMapper.update(dto.toVO());
       } catch (Exception e) { log.error("게시글 삭제 처리 실패 {}", e); }

       return dto.getCommunityId();
    }

    //커뮤니티 조회수 카운트
    @Override
    public Integer getHits(Long id, Integer views) {
        try {
            communityMapper.updateViews(id, views+1);
        } catch (Exception e) { log.error("게시글 조회수 카운트 실패 {}", e); }

        return views+1;
    }
}
