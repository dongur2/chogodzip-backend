CREATE TABLE MESSAGE (
                         message_id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 메시지 ID
                         chatroom_id BIGINT NOT NULL,                   -- 채팅방 ID (CHAT_ROOM 테이블 참조)
                         sender_id BIGINT NOT NULL,                     -- 발신자 ID (MEMBER 테이블 참조)
                         content TEXT NOT NULL,                         -- 메시지 내용
                         send_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 메시지 전송 시간
                         read_status BOOLEAN DEFAULT FALSE,             -- 읽음 여부
                         FOREIGN KEY (chatroom_id) REFERENCES CHAT_ROOM(chatroom_id) ON DELETE CASCADE,
                         FOREIGN KEY (sender_id) REFERENCES member(mno) ON DELETE CASCADE
);
