# drop table if exists USER_OPT_INFO;
# drop table if exists INTEREST_ROOM;
# drop table if exists USER_AUTH;
# drop table if exists USER;

-- 사용자 정보 테이블
create table USER
(
    USER_ID     BIGINT PRIMARY KEY AUTO_INCREMENT,
    USERNAME    VARCHAR(50),            -- 소셜로그인아이디(이메일)
    NICKNAME    VARCHAR(30)  NOT NULL UNIQUE,
    LOGIN_TYPE  CHAR(10),               -- SNS (KAKAO/GOOGLE/NAVER) -- 이후 SNS 가입 확장 위한 추가
    PIC         VARCHAR(500) DEFAULT 'https://chogodzip.s3.ap-northeast-2.amazonaws.com/DF07.png', -- 프로필 사진
    CREATED_AT  DATETIME DEFAULT now(),
    MODIFIED_AT DATETIME DEFAULT now(),
    IS_DELETED  TINYINT(1) DEFAULT 0 check (USER.IS_DELETED in (0, 1))  -- 0: 활성화, 1:비활성
);
CREATE INDEX IDX_USER_ID ON USER (USER_ID);

-- 사용자 권한 테이블
create table USER_AUTH
(
    USER_ID BIGINT not null,       -- 사용자 id
    AUTHORITY CHAR(50) not null, -- 권한 문자열 role_admin, role_member
    primary key (USER_ID, AUTHORITY),   -- 복합키
    constraint fk_authorities_users foreign key (USER_ID) references USER (USER_ID) ON DELETE CASCADE -- 유저가 삭제되면 해당 추가정보도 삭제
);

-- 목데이터
insert into USER values(default, 'testuser@email.com', '김부둥', 'KAKAO', default, default, default, default);
insert into USER values(default, 'testadmin@email.com', '관리자', null, default, default, default, default);
insert into USER_AUTH(USER_ID, AUTHORITY) values (1, 'role_member'), (2, 'role_member'), (2, 'role_admin');
commit;

-- 사용자 추가정보 테이블
create table USER_OPT_INFO(
    UOPTI_ID        BIGINT PRIMARY KEY AUTO_INCREMENT,
    USER_ID         BIGINT NOT NULL,
    REAL_REGION     VARCHAR(255),
    REAL_DETAIL     VARCHAR(255),
    INTEREST_SI     VARCHAR(50),
    INTEREST_GU     VARCHAR(50),
    INTEREST_DONG   VARCHAR(50),
    CONSTRAINT FK_USER_OPT_INFO_USER FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID) ON DELETE CASCADE -- 유저가 삭제되면 해당 추가정보도 삭제
);

-- 사용자 관심 매물 테이블
create table INTEREST_ROOM(
    IROOM_ID    BIGINT PRIMARY KEY AUTO_INCREMENT,
    USER_ID     BIGINT NOT NULL,
    ROOM_ID     BIGINT, -- 해당 매물이 삭제될 경우 null처리 -> 프론트에서 처리
    CREATED_AT  DATETIME DEFAULT now(),
    CONSTRAINT FK_INTEREST_ROOM_USER FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID) ON DELETE CASCADE, -- 유저가 삭제되면 해당 추가정보도 삭제
    CONSTRAINT FK_INTEREST_ROOM_ROOM FOREIGN KEY (ROOM_ID) REFERENCES ROOM(ROOM_ID)
);