package com.kb.room.vo;

import lombok.*;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class RoomWithLoan {
    private Long roomLoanId;
    private Integer roomId;
    private Long loanId;
}
