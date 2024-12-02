package com.kb.room.service.search;

import com.kb.room.dto.response.search.CoordinateDTO;
import com.kb.room.vo.search.University;
import com.kb.room.mapper.search.UniversityMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
public class UniversityService {
    private final UniversityMapper univMapper;

    public List<University> getAllUniversities() {
        return univMapper.selectAllUniversities();
    }

    public CoordinateDTO findCoordinatesByUniversityName(String name) {
        return univMapper.selectCoordinatesByUniversityName(name);
    }
}
