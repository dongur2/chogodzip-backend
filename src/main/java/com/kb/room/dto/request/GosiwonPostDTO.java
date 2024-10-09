package com.kb.room.dto.request;

import lombok.*;


@Setter @Getter
@Builder @ToString
@NoArgsConstructor @AllArgsConstructor
public class GosiwonPostDTO {
    private String category;
    private BasicInfo basicInfo;
    private LoanInfo loanInfo;
    private FacilitiesInfo facilitiesInfo;
    private BuildingInfo buildingInfo;
}
