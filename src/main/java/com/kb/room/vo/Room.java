package com.kb.room.vo;

import lombok.*;
import java.math.BigDecimal;
import java.util.Date;


@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Room {
    private Integer roomId;
    private Long userId; //member.mno
    private BigDecimal roomLat;
    private BigDecimal roomLong;
    private String thumbnail; //url
    private String pics; //url
    private String address; //전체주소
    private String houseTypeCd; //HOUTP00001 (고시원), HOUTP00003 (원룸텔)
    private String houseTypeNm; //고시원, 원룸텔
    private String dongLiNm; //크롤링데이터만 포함
    private Boolean canLoan;
    private Date createdAt;
    private Date updatedAt;
}