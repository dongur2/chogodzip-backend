package com.kb.search.service;

import com.kb.search.dto.SearchParam;
import com.kb.search.dto.University;
import com.kb.search.mapper.SearchMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Log4j
@Service
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
public class SearchService {

    private final SearchMapper searchMapper;

    public List<University> getAllUniversity() {

        return searchMapper.findAllUniversity();
    }

    public SearchParam getOneUniversity(String name) {

        return searchMapper.findOneUniversity(name);
    }

}
