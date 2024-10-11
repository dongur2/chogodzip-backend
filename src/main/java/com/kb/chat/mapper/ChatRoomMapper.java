package com.kb.chat.mapper;

import com.kb.chat.dto.ChatRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ChatRoomMapper {
    ChatRoom findChatRoom(Map<String, Object> map);
    void insertChatRoom(ChatRoom chatRoom);
    void deleteChatRoom(@Param("chatRoomId") Long chatRoomId);git
}
