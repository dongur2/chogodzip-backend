package com.kb.community.service;

import com.kb.community.dto.request.CommentModifyDTO;
import com.kb.community.dto.request.CommentPostDTO;
import com.kb.community.dto.response.CommentDetailDTO;
import com.kb.community.mapper.CommunityCmtMapper;
import com.kb.user.mapper.UserMapper;
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
public class CommunityCmtServiceI implements CommunityCmtService{
    @Autowired private CommunityCmtMapper mapper;
    @Autowired private UserMapper memberMapper;

    //커뮤니티 글에 대한 모든 댓글 조회
    @Override
    public List<CommentDetailDTO> getAll(Long communityId) {
        return mapper.findAllByCommunityId(communityId);
    }

    //댓글 작성
    @Override @Transactional
    public CommentDetailDTO save(CommentPostDTO dto) {
        Long savedId = mapper.saveCmt(dto);
        return mapper.findOneByCmtId(savedId);
    }

    //댓글 수정
    @Override @Transactional
    public CommentDetailDTO modify(CommentModifyDTO dto) {
        mapper.updateCmt(dto);
        return mapper.findOneByCmtId(dto.getCmtId());
    }

    //댓글 삭제
    @Override @Transactional
    public void delete(Long cmtId) {
        mapper.deleteCmtByCmtId(cmtId);
    }
}
