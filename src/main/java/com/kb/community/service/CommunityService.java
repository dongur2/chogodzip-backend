package com.kb.community.service;


import com.kb.community.dto.request.CommunityPostDTO;
import com.kb.community.dto.request.CommunityModifyDTO;
import com.kb.community.dto.response.CommunityDetailDTO;
import com.kb.community.dto.response.CommunityListDTO;

import java.util.List;


public interface CommunityService {
    List<CommunityListDTO> getAll();
    CommunityDetailDTO getDetail(Long id);
    Long add(CommunityPostDTO dto);
    void delete(Long id);
    Long modifyPostContent(CommunityModifyDTO dto);
    Integer getHits(Long id, Integer views);
}
