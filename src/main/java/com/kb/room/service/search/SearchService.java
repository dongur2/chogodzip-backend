package com.kb.room.service.search;

import com.kb.room.dto.response.search.ResourceWithCoordinateDTO;

import java.util.List;

public interface SearchService {
    List<ResourceWithCoordinateDTO> getAllUnivAndSubwayWithCoordinate();
}
