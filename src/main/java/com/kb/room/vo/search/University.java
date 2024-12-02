package com.kb.room.vo.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data @Builder
public class University {
    private Long uvsId;
    private String name;
    private Double uvsLat;
    private Double uvsLong;
    private List<Subway> subways;
}
