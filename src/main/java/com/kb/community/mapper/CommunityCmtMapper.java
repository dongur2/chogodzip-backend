package com.kb.community.mapper;

import com.kb.community.vo.CommunityCmt;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CommunityCmtMapper {
    void saveCmt(CommunityCmt cmt);
    CommunityCmt findOneByCmtId(@Param("cmtId") Long cmtId);
    List<CommunityCmt> findAllByCommunityId(@Param("communityId") Long communityId);
    void updateCmt(@Param("cmtId") Long cmtId, @Param("content") String content);
    void deleteCmtByCmtId(@Param("cmtId") Long cmtId);
}