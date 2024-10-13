package com.kb.community.service;

import com.kb.community.dto.request.CommentPostDTO;
import com.kb.community.dto.response.CommentDetailDTO;
import com.kb.community.mapper.CommunityCmtMapper;
import com.kb.community.vo.CommunityCmt;
import com.kb.member.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service @Primary
@NoArgsConstructor @AllArgsConstructor
public class CommunityCmtServiceI implements CommunityCmtService{
    @Autowired private CommunityCmtMapper mapper;
    @Autowired private MemberMapper memberMapper;


    //커뮤니티 글에 대한 모든 댓글 조회
    @Override
    public List<CommentDetailDTO> getAll(Long communityId) {
        List<CommunityCmt> voList = mapper.findAllByCommunityId(communityId);
        if(voList != null && !voList.isEmpty()) return voList.stream().map(CommentDetailDTO::from).toList();
        else return null;
    }

    //댓글 작성
    @Override
    public CommentDetailDTO save(CommentPostDTO dto) {
        CommunityCmt vo = dto.toVO();
        Long mno = memberMapper.selectById(vo.getMemberId()).getMno();
        vo.setMNo(mno);

        mapper.saveCmt(vo);
        return CommentDetailDTO.from(vo);
    }

    //댓글 삭제
    @Override
    public void delete(Long cmtId) {
        mapper.deleteCmtByCmtId(cmtId);
    }
}
