package com.kb.community.mapper;

import com.kb.community.vo.Community;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommunityMapper {
    List<Community> findAll();
    Community findById(@Param("communityId") Long communityId);
    Long save(Community community);
}