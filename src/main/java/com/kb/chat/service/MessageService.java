package com.kb.chat.service;

import com.kb.chat.dto.Message;
import com.kb.chat.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {
    private final MessageMapper messageMapper;

    public MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public List<Message> getMessagesByChatRoom(Long chatRoomId) {
        return messageMapper.findMessagesByChatRoom(chatRoomId);
    }

    public void sendMessage(Long chatroomId, Long senderId, String content){
        Message message = new Message();
        message.setChatroomId(chatroomId);
        message.setSenderId(senderId);
        message.setContent(content);
        message.setReadStatus(false);
        messageMapper.insertMessage(message);
    }

    public int getUnreadMessageCount(Long chatroomId, Long senderId) {
        return messageMapper.countUnreadMessages(chatroomId, senderId);
    }

    public void markMessagesAsRead(Long chatRoomId, Long senderId) {
        Map<String, Object> params = new HashMap<>();
        params.put("chatRoomId", chatRoomId);
        params.put("senderId", senderId);
        messageMapper.updateReadStatus(params);
    }
}
