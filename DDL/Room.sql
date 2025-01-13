DROP TABLE IF EXISTS ONETWOROOM;
DROP TABLE IF EXISTS SHAREHOUSE;
DROP TABLE IF EXISTS GOSIWON;
DROP TABLE IF EXISTS ROOM;

-- HOUSE_TYPE_CD & HOUSE_TYPE_NM
-- HOUTP00001:고시원, HOUTP00003:원룸텔, HOUTP00006: 모텔(5개 있는데 원룸텔로 분류)
-- HOUTP00002:쉐어하우스, HOUTP00004: 코리빙하우스, HOUTP00005: 게스트하우스
-- HOUTP00008:원･투룸, HOUTP00009:오피스텔

-- GENDER_LIMIT
-- GENDR00001:구분없음, GENDR00002:남성, GENDR00003:여성, GENDR00004:분리


-- 부동산 (ROOM)
DROP TABLE IF EXISTS ROOM;
CREATE TABLE ROOM
(
    -- 크롤링에서 넘어오는 데이터
    ROOM_ID         BIGINT PRIMARY KEY AUTO_INCREMENT,
    ROOM_LAT        DECIMAL(10, 7),     -- 위도(latitude)
    ROOM_LONG       DECIMAL(10, 7),     -- 경도(longitude)
    THUMBNAIL       VARCHAR(2000),      -- 썸네일
    ADDRESS         VARCHAR(255),       -- 주소
    HOUSE_TYPE_CD   VARCHAR(255),       -- HOUTP00001:고시원, HOUTP00002:쉐어하우스, HOUTP00003:원룸텔, HOUTP00004:원/투룸, HOUTP00005:오피스텔
    HOUSE_TYPE_NM   VARCHAR(255),
    DONGLI_NM       VARCHAR(22),        -- '봉천동'

    -- 방별 공통
    USER_ID         BIGINT,

    ROOM_CNT        INT,                -- 방 개수
    ROOM_NAME       VARCHAR(255),
    ROOM_ADDR_FL    VARCHAR(255),       -- 층

    CAN_LOAN    TINYINT(1),             -- 0:불가 1:가능 -> 가능이면 ROOM_LOAN 쿼리

    POSTCODE                CHAR(5), -- NULL로 변경

    PRIVATE_FACILITIES      VARCHAR(255), -- |기준으로 split 목업
    SERVICES                VARCHAR(255), -- |기준으로 split 목업
    LANGUAGES               VARCHAR(255), -- |기준으로 split 목업
    ETC                     VARCHAR(255), -- |기준으로 split 목업

    DESCRIPTION             TEXT, -- 목업
    PICS                    TEXT, -- 방사진(최대 3장), |기준으로  split

    PRICE_MIN               INT NOT NULL, -- "PRICE_MIN"
    PRICE_MAX               INT NOT NULL, -- "PRICE_MAX"
    DEPOSIT_MIN             INT, -- "DEPOSIT_MIN"
    DEPOSIT_MAX             INT, -- "DEPOSIT_MAX"
    MAINTENANCE_FEE         INT, -- "MAINTENANCE_FEE"

    CONTRACT_MIN            INT, -- 일수

    FACILITY_HEATING        VARCHAR(255),
    FACILITY_COOLING        VARCHAR(255),
    FACILITY_LIFE           VARCHAR(255),
    FACILITY_SECURITY       VARCHAR(255),

    BUILDING_TYPE           VARCHAR(10), -- 0:상가건물 1:단독 2:공동
    CAN_PARKING             TINYINT(1), -- 0:불가 1:가능
    HAS_ELEVATOR            TINYINT(1), -- 0:없음 1:있음

    IS_SOLD_OUT             TINYINT(1), -- 0:판매중 1:판매완료 -- 탈락

    UPDATED_AT  DATETIME default now(),
    CREATED_AT  DATETIME default now(),

    CONSTRAINT FK_ROOM_USER FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID)
) AUTO_INCREMENT = 1000001;

-- 고시원
DROP TABLE IF EXISTS GOSIWON;
CREATE TABLE GOSIWON
(
    GSW_ID                  BIGINT PRIMARY KEY AUTO_INCREMENT,

    -- 크롤링
    ROOM_ID                 BIGINT,
    TITLE                   VARCHAR(255) NOT NULL, -- "NAME"
    GENDER_LIMIT            VARCHAR(22), -- GENDR00001:구분없음, GENDR00002:남성, GENDR00003:여성, GENDR00004:분리
    AGE_MAX                 INT, -- 0 or NULL 이면 없음 // "ENTER AGE_MAX"
    AGE_MIN                 INT, -- 0 or NULL 이면 없음 // "ENTER AGE_MIN"

    CONSTRAINT FK_GOSIWON_ROOM FOREIGN KEY (ROOM_ID) REFERENCES ROOM(ROOM_ID) ON DELETE CASCADE
)  AUTO_INCREMENT = 2000001;

-- 쉐어하우스 (+코리빙,게스트하우스)
DROP TABLE IF EXISTS SHAREHOUSE;
CREATE TABLE SHAREHOUSE
(
    SHH_ID                  BIGINT PRIMARY KEY AUTO_INCREMENT,

    -- 크롤링
    ROOM_ID                 BIGINT,
    TITLE                   VARCHAR(255) NOT NULL, -- "NAME"
    GENDER_LIMIT            VARCHAR(22), -- GENDR00001:구분없음, GENDR00002:남성, GENDR00003:여성, GENDR00004:분리
    AGE_MAX                 INT, -- 0 or NULL 이면 없음 // "ENTER AGE_MAX"
    AGE_MIN                 INT, -- 0 or NULL 이면 없음 // "ENTER AGE_MIN"
    ACCOMO_CNT              INT, -- 전체 숙소 수용 가능 인원

    CONSTRAINT FK_SHAREHOUSE_ROOM FOREIGN KEY (ROOM_ID) REFERENCES ROOM(ROOM_ID) ON DELETE CASCADE
)  AUTO_INCREMENT = 3000001;

-- 원/투룸
DROP TABLE IF EXISTS ONETWOROOM;
CREATE TABLE ONETWOROOM
(
    OTR_ID                  BIGINT PRIMARY KEY AUTO_INCREMENT,

    -- 크롤링
    ROOM_ID                 BIGINT,
    DETAIL_NAME             VARCHAR(50), -- "NAME" // 해당 호수 또는 층
    ROOM_TYPE               VARCHAR(10), -- 원룸(오픈/분리/복층형)/투룸/쓰리룸 이상

    CONSTRAINT FK_ONETWOROOM_ROOM FOREIGN KEY (ROOM_ID) REFERENCES ROOM(ROOM_ID) ON DELETE CASCADE
)  AUTO_INCREMENT = 4000001;
