package com.kb.search.mapper;

import com.kb.search.dto.Subway;
import com.kb.search.dto.University;
import java.util.List;

public interface SearchMapper {

    List<Subway> findAllSubway();

    List<University> findAllUniversity();
}
