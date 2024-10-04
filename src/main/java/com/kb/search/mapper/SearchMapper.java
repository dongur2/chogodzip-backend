package com.kb.search.mapper;

import com.kb.search.dto.SearchParam;
import com.kb.search.dto.University;
import java.util.List;

public interface SearchMapper {

    List<University> findAllUniversity();

    SearchParam findOneUniversity(String name);
}
