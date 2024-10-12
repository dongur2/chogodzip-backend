package com.kb.chat.mapper;
import com.kb.chat.dto.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MessageMapper {
    List<Message> findMessagesByChatRoom(Long chatroomId);
    void insertMessage(Message message);
    int countUnreadMessages(@Param("chatroomId") Long chatroomId, @Param("senderId") Long senderId);
    void updateReadStatus(Long chatRoomId, Long senderId);

    void updateReadStatus(Map<String, Object> params);
}
