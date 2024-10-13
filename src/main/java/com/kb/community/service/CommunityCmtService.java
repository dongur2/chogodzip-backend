package com.kb.community.service;

import com.kb.community.dto.response.CommentDetailDTO;

import java.util.List;

public interface CommunityCmtService {
    List<CommentDetailDTO> getAll(Long communityId);
    void delete(Long cmtId);
}
