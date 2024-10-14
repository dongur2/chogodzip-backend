package com.kb.chat.controller;

import com.kb.chat.dto.ChatRoom;
import com.kb.chat.dto.Message;
import com.kb.chat.dto.MessageDto;
import com.kb.chat.service.ChatRoomService;
import com.kb.chat.service.MessageService;
import com.kb.member.dto.Member;
import com.kb.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping(value = "/api/chat", produces = "application/json")
@RequiredArgsConstructor
public class ChatController {
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;
    private final MemberService memberService;

    @GetMapping("/owner")
    public ResponseEntity<Member> getOwnerInfo(Long userId){
        Member mem = memberService.getIdMem(userId);
        return ResponseEntity.ok(mem);
    }

    // 특정 매물에 대해 채팅방이 있는지 조회하고, 없으면 새로 생성
    @GetMapping("/room")
    public ResponseEntity<ChatRoom> getOrCreateChatRoom(@RequestParam Long roomId, @RequestParam Long senderId, @RequestParam Long receiverId) {
        ChatRoom chatRoom = chatRoomService.findOrCreateChatRoom(roomId, senderId, receiverId);
        return ResponseEntity.ok(chatRoom);
    }

    @GetMapping("/findchatRoomNum")
    public ResponseEntity<Integer> getChatRoomNum(@RequestParam Long roomId, @RequestParam String userName){
        Long userId  = memberService.searchOneMember(userName);
        int result = chatRoomService.getNum(roomId, userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/chatList")
    public ResponseEntity<List<ChatRoom>> getAllChatRoom(String userName){
        Long userId = memberService.searchOneMember(userName);
        List<ChatRoom> getChatRoom = chatRoomService.getChatRooms(userId);
        return ResponseEntity.ok(getChatRoom);
    }

    // 메시지 조회
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages(@RequestParam Long chatRoomId) {
        List<Message> messages = messageService.getMessagesByChatRoom(chatRoomId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/messages/mark-read")
    public ResponseEntity<Void> markMessagesAsRead(@RequestParam Long chatRoomId, @RequestParam Long senderId) {
        messageService.markMessagesAsRead(chatRoomId, senderId);
        return ResponseEntity.ok().build();
    }

    // 메시지 전송
    @PostMapping("/message")
    public ResponseEntity<Void> sendMessage(@RequestBody MessageDto messageDto) {
        messageService.sendMessage(messageDto.getChatroomId(), messageDto.getSenderId(), messageDto.getContent());
        return ResponseEntity.ok().build();
    }

    // 채팅방 삭제
    @DeleteMapping("/room/{chatRoomId}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable Long chatRoomId) {
        chatRoomService.deleteChatRoom(chatRoomId);
        return ResponseEntity.ok().build();
    }
    // 특정 채팅방에서 수신자가 읽지 않은 메시지 수 조회
    @GetMapping("/messages/unread-count")
    public int countUnreadMessages(@RequestParam Long chatroomId, @RequestParam Long senderId) {
        return messageService.getUnreadMessageCount(chatroomId, senderId);
    }


}

