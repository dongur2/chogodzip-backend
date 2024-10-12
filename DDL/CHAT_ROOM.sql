CREATE TABLE CHAT_ROOM (
                           chatroom_id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 채팅방 ID
                           room_id BIGINT NOT NULL,                        -- 매물 ID (ROOM의 FK)
                           sender_id BIGINT NOT NULL,                      -- 발신자 ID (MEMBER의 FK)
                           receiver_id BIGINT NOT NULL,                    -- 수신자 ID (MEMBER의 FK)
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 채팅방 생성 시간
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 마지막 업데이트 시간
                           FOREIGN KEY (room_id) REFERENCES ROOM(room_id) ON DELETE CASCADE,
                           FOREIGN KEY (sender_id) REFERENCES member(mno) ON DELETE CASCADE,
                           FOREIGN KEY (receiver_id) REFERENCES member(mno) ON DELETE CASCADE
);

