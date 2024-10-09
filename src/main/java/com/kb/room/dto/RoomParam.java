package com.kb.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RoomParam {
    String loanType;
    String roomType;
    String deposit;
    String price;
    String gender;
    String lat;
    String lng;
}
