package com.kb.community.service;


import com.kb.community.dto.request.CommunityPostDTO;
import com.kb.community.dto.response.CommunityDetailDTO;


public interface CommunityService {
    CommunityDetailDTO getDetail(Long id);
    Long add(CommunityPostDTO dto);
}
