-- 1011 자취방 테이블 추가
-- room 에 room_id, user_id, room_lat, room_long, thumbnail,
-- can_loan, updated_at, created_at,
USE chogodzip;
DROP TABLE IF EXISTS JACHI;
CREATE TABLE JACHI
(
    JACHI_ID          BIGINT PRIMARY KEY AUTO_INCREMENT,
    ROOM_ID           BIGINT,
    PRICE_MIN         INT    NOT NULL,-- 자취방은 PRICE MIN밖에 없음
    DEPOSIT_MIN       INT    NOT NULL, -- 자취방은 MIN만
    MAINTENANCE_FEE   INT    NOT NULL, -- 자취방은 MIN만
    FLOOR             DOUBLE NOT NULL,
    NAME              INT    NOT NULL, -- 호수를 의미함. 201호 이런거

    ROOM_SIZE         DOUBLE, -- 평수
    REAL_SIZE         DOUBLE, -- 실평
    RENT_TYPE         TINYINT(1), -- 0이면 jeonse 1이면 monthly 2면 half(반전세)
    ROOM_CNT          DOUBLE, -- 방 개수
    TOILET_CNT        INT    NULL, -- 화장실 개수
    SUN_DIR           varchar(22), -- 주실방향 ( 0:south / 1:north /2:east 3:west)
    AVA_MOVIN_DATE    DATE, -- 입주 가능 날짜
    AVA_MOVIN_DIR     TINYINT(1), -- 즉시 입주 가능 여부 : 0이면 가능 1이면 불가능
    PARK_AVAIL        TINYINT(1), -- 주차 가능 여부 : 0이면 가능 1이면 불가능

    LOAN_ID           TINYINT(1),

    FACILITY_HEATING  VARCHAR(255),
    FACILITY_COOLING  VARCHAR(255),
    FACILITY_LIFE     VARCHAR(255),
    FACILITY_SECURITY VARCHAR(255),

    DURATION_MIN      DOUBLE NOT NULL,

    BUILDING_TYPE     TINYINT(1), -- 0:상가건물 1:단독 2:공동
    CAN_PARKING       TINYINT(1), -- 0:불가 1:가능
    HAS_ELEVATOR      TINYINT(1), -- 0:없음 1:있음

    APPROVE_DATE     DATE, -- 사용승인일

    IS_SOLD_OUT             TINYINT(1), -- 0:판매중 1:판매완료 -- 탈락
    CONSTRAINT fk_room_id
        FOREIGN KEY (ROOM_ID)
            REFERENCES ROOM(ROOM_ID)
            ON DELETE CASCADE
            ON UPDATE CASCADE

);

ALTER TABLE JACHI
    MODIFY COLUMN DURATION_MIN DOUBLE NULL;














