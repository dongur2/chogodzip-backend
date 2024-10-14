package com.kb.chat.service;

import com.kb.chat.dto.ChatRoom;
import com.kb.chat.mapper.ChatRoomMapper;
import com.kb.member.dto.Member;
import com.kb.member.mapper.MemberMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
@NoArgsConstructor @AllArgsConstructor
public class ChatRoomService {

    @Autowired
    private ChatRoomMapper chatRoomMapper;

    @Autowired
    private MemberMapper memberMapper;

    public ChatRoom findOrCreateChatRoom(Long roomId, Long senderId, Long receiverId) {
        Member sender = memberMapper.selectByNo(senderId);
        if (sender == null) {
            throw new IllegalArgumentException("Invalid senderId: " + senderId);
        }
        Long senderMno = sender.getMno();

        Member receiver = memberMapper.selectByNo(receiverId);
        if (receiver == null) {
            throw new IllegalArgumentException("Invalid receiverId: " + receiverId);
        }
        Long receiverMno = receiver.getMno();

        Map<String, Object> map = new HashMap<>();
        map.put("roomId", roomId);
        map.put("senderId", senderMno);
        map.put("receiverId", receiverMno);

        ChatRoom chatRoom = chatRoomMapper.findChatRoom(map);
        if (chatRoom == null) {
            chatRoom = new ChatRoom();
            chatRoom.setRoomId(roomId);
            chatRoom.setSenderId(senderMno);
            chatRoom.setReceiverId(receiverMno);
            chatRoomMapper.insertChatRoom(chatRoom);
        }
        return chatRoom;
    }


    public void deleteChatRoom(Long chatRoomId) {
        chatRoomMapper.deleteChatRoom(chatRoomId);
    }

    public List<ChatRoom> getChatRooms(Long userId) {
        return chatRoomMapper.getAllChatRoom(userId);
    }

    public int getNum(Long roomId, Long userId) {
        return chatRoomMapper.getNum(roomId,userId);
    }
}
