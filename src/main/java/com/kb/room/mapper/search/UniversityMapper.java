package com.kb.room.mapper.search;

import com.kb.room.dto.response.search.CoordinateDTO;
import com.kb.room.vo.search.University;
import java.util.List;

public interface UniversityMapper {
    List<University> selectAllUniversities();
    CoordinateDTO selectCoordinatesByUniversityName(String name);
}
