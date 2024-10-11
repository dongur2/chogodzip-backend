package com.kb.chat.mapper;
import com.kb.chat.dto.Message;

import java.util.List;

public interface MessageMapper {
    List<Message> findMessagesByChatRoom(Long chatroomId);
    void insertMessage(Message message);
}
