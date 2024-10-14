package com.kb.room.dto.response;

import com.kb.room.vo.Gosiwon;
import lombok.*;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class RoomHomeDTO {
    private Integer roomId;
    private String thumbnail;
    private String address;
    private String type; // room.houseTypeNm

    private String title;
    private Integer priceMin;
    private Integer priceMax;
    private Integer depositMin;
    private Integer depositMax;
    private Integer maintenanceFee;
    private String genderLimit;

    private Boolean isSoldOut;

    //VO => DTO
    public static RoomHomeDTO from(Gosiwon vo) {
        return RoomHomeDTO.builder()
                .roomId(vo.getRoom().getRoomId())
                .thumbnail(vo.getRoom().getThumbnail())
                .address(vo.getRoom().getAddress())
                .type(vo.getRoom().getHouseTypeNm())
                .title(vo.getTitle())
                .priceMin(vo.getPriceMin())
                .priceMax(vo.getPriceMax())
                .depositMin(vo.getDepositMin())
                .depositMax(vo.getDepositMax())
                .maintenanceFee(vo.getMaintenanceFee())
                .genderLimit(vo.getGenderLimit())
                .isSoldOut(vo.getIsSoldOut())
                .build();
    }
}
