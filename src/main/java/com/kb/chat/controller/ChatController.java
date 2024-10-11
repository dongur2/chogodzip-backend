package com.kb.chat.controller;

import com.kb.chat.dto.ChatRoom;
import com.kb.chat.dto.Message;
import com.kb.chat.dto.MessageDto;
import com.kb.chat.service.ChatRoomService;
import com.kb.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = "/api/chat", produces = "application/json")
public class ChatController {
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;

    public ChatController(ChatRoomService chatRoomService, MessageService messageService) {
        this.chatRoomService = chatRoomService;
        this.messageService = messageService;
    }

    // 특정 매물에 대해 채팅방이 있는지 조회하고, 없으면 새로 생성
    @GetMapping("/room")
    public ResponseEntity<ChatRoom> getOrCreateChatRoom(@RequestParam Long roomId, @RequestParam Long senderId, @RequestParam Long receiverId) {
        ChatRoom chatRoom = chatRoomService.findOrCreateChatRoom(roomId, senderId, receiverId);
        return ResponseEntity.ok(chatRoom);
    }

    // 특정 채팅방의 모든 메시지 조회
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages(@RequestParam Long chatRoomId) {
        List<Message> messages = messageService.getMessagesByChatRoom(chatRoomId);
        return ResponseEntity.ok(messages);
    }

    // 메시지 전송
    @PostMapping("/message")
    public ResponseEntity<Void> sendMessage(@RequestBody MessageDto messageDto) {
        messageService.sendMessage(messageDto.getChatroomId(), messageDto.getSenderId(), messageDto.getContent());
        return ResponseEntity.ok().build();
    }
}

