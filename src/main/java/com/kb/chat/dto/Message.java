package com.kb.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Message {
    private Long messageId;
    private Long chatroomId;
    private Long senderId;
    private String content;
    private Timestamp sendTime;
    private Boolean readStatus;
}
