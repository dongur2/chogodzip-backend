package com.kb.community.mapper;

import com.kb.community.dto.request.CommunityModifyDTO;
import com.kb.community.dto.request.CommunityPostDTO;
import com.kb.community.dto.response.CommunityDetailDTO;
import com.kb.community.dto.response.CommunityListDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommunityMapper {
    List<CommunityListDTO> findAll();
    CommunityDetailDTO findById(@Param("communityId") Long communityId);

    Long save(CommunityPostDTO community);
    Long delete(@Param("communityId") Long communityId);

    Long update(CommunityModifyDTO community);
    Integer updateViews(@Param("communityId") Long communityId, @Param("views") Integer views);
}