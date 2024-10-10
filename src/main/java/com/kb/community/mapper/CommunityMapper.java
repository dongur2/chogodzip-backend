package com.kb.community.mapper;

import com.kb.community.vo.Community;
import org.apache.ibatis.annotations.Param;

public interface CommunityMapper {
    Community findById(@Param("communityId") Long communityId);
    Long save(Community community);
}