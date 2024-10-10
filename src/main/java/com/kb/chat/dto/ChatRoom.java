package com.kb.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChatRoom {
    private Long chatroomId;
    private Long roomId;
    private Long senderId;
    private Long receiverId;
}
