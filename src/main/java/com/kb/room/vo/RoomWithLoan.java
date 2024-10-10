package com.kb.room.vo;

import lombok.*;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class RoomWithLoan {
    private Long roomLoanId;
    private Long roomId;
    private Long loanId;
}
