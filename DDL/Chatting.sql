-- 채팅방
CREATE TABLE CHAT_ROOM (
           CR_ID        BIGINT AUTO_INCREMENT PRIMARY KEY,          -- 채팅방 ID
           ROOM_ID      BIGINT,                                     -- 매물 ID (ROOM의 FK)
           SENDER_ID    BIGINT,                                     -- 발신자 ID (USER의 FK)
           RECEIVER_ID  BIGINT,                                     -- 수신자 ID (USER의 FK)
           CREATED_AT   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,        -- 채팅방 생성 시간
           UPDATED_AT   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 마지막 업데이트 시간
           FOREIGN KEY (ROOM_ID) REFERENCES ROOM(ROOM_ID),
           FOREIGN KEY (SENDER_ID) REFERENCES USER(USER_ID),
           FOREIGN KEY (RECEIVER_ID) REFERENCES USER(USER_ID)
);

-- 메세지
CREATE TABLE MESSAGE (
                         CM_ID      BIGINT AUTO_INCREMENT PRIMARY KEY,       -- 메시지 ID
                         CR_ID      BIGINT,                                  -- 채팅방 ID (CHAT_ROOM 테이블 참조)
                         SENDER_ID  BIGINT,                                  -- 발신자 ID (USER 테이블 참조)
                         CONTENT    TEXT,                                    -- 메시지 내용
                         CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,     -- 메시지 전송 시간
                         IS_READ    TINYINT(1) DEFAULT 0,                    -- 읽음 여부
                         FOREIGN KEY (CR_ID) REFERENCES CHAT_ROOM(CR_ID),
                         FOREIGN KEY (SENDER_ID) REFERENCES USER(USER_ID)
);