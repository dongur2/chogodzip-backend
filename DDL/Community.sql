-- DROP TABLE IF EXISTS COMMUNITY_CMT;
-- DROP TABLE IF EXISTS COMMUNITY;


-- 커뮤니티
CREATE TABLE COMMUNITY
(
    COMMUNITY_ID    BIGINT PRIMARY KEY AUTO_INCREMENT,
    USER_ID         BIGINT, -- null 처리될 경우(탈퇴) 프론트에서 처리
    TITLE           VARCHAR(100),
    CONTENT         LONGTEXT,
    TAG             VARCHAR(50), -- REPI(정책,투자)/REHT(핫이슈)/RERV(후기)/CTRV(계약,입주후기)/ITRV(인테리어)/LNQS(대출)/LTQS(분양,청약)
    PICS            TEXT, -- 작성글에 포함된 이미지 이름.확장자 "|"로 구분
    VIEWS           INT default 0,
    IS_DELETED      TINYINT(1) default 0, -- 0:존재 1:삭제
    UPDATED_AT      DATETIME default now(),
    CREATED_AT      DATETIME default now(),

    CONSTRAINT FK_COMMUNITY_USER FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID)
);

-- 댓글
CREATE TABLE COMMUNITY_CMT
(
    CMT_ID              BIGINT PRIMARY KEY AUTO_INCREMENT,
    COMMUNITY_ID        BIGINT,
    USER_ID             BIGINT,  -- null 처리될 경우(탈퇴) 프론트에서 처리
    CONTENT             VARCHAR(300),
    CREATED_AT          DATETIME DEFAULT now(),
    UPDATED_AT          DATETIME DEFAULT now(),
    IS_DELETED          TINYINT(1) default 0, -- 0:존재 1:삭제

    CONSTRAINT FK_COMMUNITY_CMT_COMMUNITY FOREIGN KEY (COMMUNITY_ID) REFERENCES COMMUNITY(COMMUNITY_ID) ON DELETE CASCADE,
    CONSTRAINT FK_COMMUNITY_CMT_USER FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID)
);
