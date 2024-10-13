package com.kb.community.mapper;

import com.kb.community.vo.Community;
import com.kb.community.vo.CommunityCmt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommunityMapper {
    List<Community> findAll();
    Community findById(@Param("communityId") Long communityId);
    Long save(Community community);
    Long updateIsDeletedByCommunityId(@Param("communityId") Long communityId);
    Long update(Community community);
    Integer updateViews(@Param("communityId") Long communityId, @Param("views") Integer views);
}