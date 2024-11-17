-- 사용자 정보 테이블
drop table if exists USER;
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

-- 목데이터
insert into USER values(default, 'testuser@email.com', '김부둥', 'KAKAO', default, default, default, default);
insert into USER values(default, 'testadmin@email.com', '관리자', null, default, default, default, default);
commit;

select * from USER;

-- 사용자 권한 테이블
drop table if exists USER_AUTH;
create table USER_AUTH
(
    USER_ID BIGINT not null,       -- 사용자 id
    AUTHORITY char(50) not null, -- 권한 문자열 role_admin, role_member
    primary key (USER_ID, AUTHORITY),   -- 복합키
    constraint fk_authorities_users foreign key (USER_ID) references USER (USER_ID)
);

-- 사용자 권한 추가
insert into USER_AUTH(USER_ID, AUTHORITY)
values (1, 'role_member'), (2, 'role_member'), (2, 'role_admin');

select * from USER_AUTH order by AUTHORITY;
