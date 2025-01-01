package com.kb.room.mapper.search;

import com.kb.room.dto.response.search.ResourceWithCoordinateDTO;
import java.util.List;

public interface SearchMapper {
    List<ResourceWithCoordinateDTO> selectAllUnivAndSubwayWithNameAndCoordinate();
}
