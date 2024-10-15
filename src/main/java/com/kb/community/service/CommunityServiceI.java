package com.kb.community.service;

import com.kb.community.dto.request.CommentModifyDTO;
import com.kb.community.dto.request.CommentPostDTO;
import com.kb.community.dto.request.CommunityModifyDTO;
import com.kb.community.dto.request.CommunityPostDTO;
import com.kb.community.dto.response.CommentDetailDTO;
import com.kb.community.dto.response.CommunityDetailDTO;
import com.kb.community.dto.response.CommunityListDTO;
import com.kb.community.mapper.CommunityMapper;
import com.kb.community.vo.Community;
import com.kb.member.mapper.MemberMapper;
import com.kb.member.service.MemberService;
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

    @Autowired private CommunityCmtService cmtService;
    @Autowired
    private MemberService memberService;

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
            dto.setMemberPic(memberService.getPicOfMember(dto.getMNo())); //프사

            //댓글 조회
            List<CommentDetailDTO> cmts = cmtService.getAll(id);
            if(cmts != null) {
                cmts.forEach(c -> c.setMemberPic(memberService.getPicOfMember(c.getMNo())));
                dto.setComments(cmts);
            }

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

    //댓글 작성
    @Override @Transactional
    public CommentDetailDTO postCmt(CommentPostDTO dto) {
        CommentDetailDTO saved = null;
        try {
           saved  = cmtService.save(dto);
        } catch (Exception e) { log.error("댓글 작성에 실패했습니다 : {}", e); }
        return saved;
    }

    //댓글 수정
    @Override @Transactional
    public CommentDetailDTO editCmt(CommentModifyDTO dto) {
        CommentDetailDTO edited = null;
        try {
            edited  = cmtService.modify(dto);
        } catch (Exception e) { log.error("댓글 수정에 실패했습니다 : {}", e); }
        return edited;
    }

    //커뮤니티 댓글 삭제
    @Override @Transactional
    public void deleteCmt(Long communityId, Long cmtId) {
        try {
            cmtService.delete(cmtId); //댓글 삭제
        } catch (Exception e) { log.error("댓글 삭제 실패 {}", e); }
    }
}
