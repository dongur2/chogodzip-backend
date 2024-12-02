-- 전철 테이블
drop table if exists ROOM_SUBWAY;
drop table if exists SUBWAY;

CREATE TABLE SUBWAY (
    CODE        CHAR(5) PRIMARY KEY NOT NULL,    -- 전철역코드
    NAME	    VARCHAR(50)	    NOT NULL,       -- 전철역명
    SBW_LAT     DECIMAL(10, 7)  NOT NULL,
    SBW_LONG    DECIMAL(10, 7)  NOT NULL,
    LINE	    varchar(10)	    NOT NULL,       -- 호선
    EX_CODE	    VARCHAR(255)	NOT NULL        -- 외부코드
);

-- 매물과 가까운 전철역 테이블
CREATE TABLE ROOM_SUBWAY (
   RS_ID	    BIGINT PRIMARY KEY AUTO_INCREMENT,
   CODE	        CHAR(5)	        NOT NULL,   -- 전철역코드
   ROOM_ID	    BIGINT          NOT NULL,
   DISTANCE	    BIGINT	        NOT NULL,   -- 전철역에서 집까지 거리
   WALK	        VARCHAR(255)	NOT NULL,	-- 전철역에서 집까지 도보거리
   CONSTRAINT FK_ROOM_SUBWAY_SUBWAY FOREIGN KEY (CODE) REFERENCES SUBWAY(CODE) ON DELETE CASCADE,
   CONSTRAINT FK_ROOM_SUBWAY_ROOM FOREIGN KEY (ROOM_ID) REFERENCES ROOM(ROOM_ID) ON DELETE CASCADE
);