package com.kb.room.service.search;

import com.kb.room.dto.response.search.ResourceWithCoordinateDTO;
import com.kb.room.mapper.search.SearchMapper;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Slf4j
@Service @Primary
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
public class SearchServiceI implements SearchService {
    private final SearchMapper searchMapper;

    @Override //모든 대학/전철 데이터 조회
    public List<ResourceWithCoordinateDTO> getAllUnivAndSubwayWithCoordinate() {
        return searchMapper.selectAllUnivAndSubwayWithNameAndCoordinate();
    }
}
