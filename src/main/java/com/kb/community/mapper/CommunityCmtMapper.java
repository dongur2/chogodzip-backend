package com.kb.community.mapper;

import com.kb.community.dto.request.CommentModifyDTO;
import com.kb.community.dto.request.CommentPostDTO;
import com.kb.community.dto.response.CommentDetailDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CommunityCmtMapper {
    Long saveCmt(CommentPostDTO cmt);
    CommentDetailDTO findOneByCmtId(@Param("cmtId") Long cmtId);
    List<CommentDetailDTO> findAllByCommunityId(@Param("communityId") Long communityId);
    void updateCmt(CommentModifyDTO cmt);
    void deleteCmtByCmtId(@Param("cmtId") Long cmtId);
}