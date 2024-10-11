package com.kb.chat.service;

import com.kb.chat.dto.ChatRoom;
import com.kb.chat.mapper.ChatRoomMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatRoomService {

    private final ChatRoomMapper chatRoomMapper;

    public ChatRoomService(ChatRoomMapper chatRoomMapper) {
        this.chatRoomMapper = chatRoomMapper;
    }

    public ChatRoom findOrCreateChatRoom(Long roomId, Long senderId, Long receiverId){

        Map<String, Object> map = new HashMap<>();
        map.put("roomId", roomId);
        map.put("senderId", senderId);
        map.put("receiverId", receiverId);

        ChatRoom chatRoom = chatRoomMapper.findChatRoom(map);
        if(chatRoom == null){
            chatRoom = new ChatRoom();
            chatRoom.setRoomId(roomId);
            chatRoom.setSenderId(senderId);
            chatRoom.setReceiverId(receiverId);
            chatRoomMapper.insertChatRoom(chatRoom);
        }
        return chatRoom;
    }
}
