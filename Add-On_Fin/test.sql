create table test(
   t_id int primary key,
   user_num int not null,
   t_title varchar(100) not null,
   t_content varchar(4000) not null,
   t_answer varchar(4000) not null,
   T_EX VARCHAR(225) NOT NULL,
   t_writer varchar(50) not null,
   t_date date default sysdate,
   t_hit int default 0,
   t_lang varchar(20) not null,
   RE_CNT int default 0,
   constraint user_num_cons foreign key (user_num) references users(user_num) on delete cascade
)

-- 코딩 테스트 댓글 테이블
CREATE TABLE TEST_REPLY(
	R_ID INT PRIMARY KEY, -- 댓글 번호
	T_ID INT NOT NULL, -- TEST게시글 번호
	USER_NUM INT NOT NULL, -- 회원번호
	R_CONTENT VARCHAR(225) NOT NULL, -- 댓글/대댓글 내용 
	R_DATE DATE DEFAULT SYSDATE, -- 댓글/대댓글 작성일
	DELETE_AT VARCHAR(1) DEFAULT 'N', -- 댓글 삭제 유무
	R_WRITER VARCHAR(20) NOT NULL, -- 작성자 == ID
	PARENT_ID INT NOT NULL, -- 대댓글 확인
	constraint t_id_cons foreign key (t_id) references test(t_id) on delete cascade,
	CONSTRAINT user_num_cons3 FOREIGN KEY (USER_NUM) REFERENCES users(user_num) on delete cascade
)
-- -------------------------------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------------------------------
-- ★ insert ★ => 필요하시면 이용하세요.
	-- USERS 테이블
insert into USERS values((select NVL(MAX(user_num),0)+1 from users), '박정은', 'je','111','01011111111','F','je@gmail.com','주소','19960927','1');
insert into USERS values((select NVL(MAX(user_num),0)+1 from users), 'GG', 'GG','111','01011111111','F','ㅎㅎ@gmail.com','GG주소','20210914','2');

	-- TEST 테이블
INSERT INTO TEST (T_ID, USER_NUM, T_TITLE, T_CONTENT, T_ANSWER,T_EX, T_WRITER, T_LANG) VALUES ((select NVL(MAX(T_ID),0)+1 from TEST),1,'코딩문제1','문제내용1','답1','출력1','je','JAVA');
INSERT INTO TEST (T_ID, USER_NUM, T_TITLE, T_CONTENT, T_ANSWER,T_EX, T_WRITER, T_LANG) VALUES ((select NVL(MAX(T_ID),0)+1 from TEST),2,'코딩문제2','문제','답','출력','j','JA');
INSERT INTO TEST (T_ID, USER_NUM, T_TITLE, T_CONTENT, T_ANSWER,T_EX, T_WRITER, T_LANG) VALUES ((select NVL(MAX(T_ID),0)+1 from TEST),2,'코딩문제3','문제','답','출력','j','JA');
INSERT INTO TEST (T_ID, USER_NUM, T_TITLE, T_CONTENT, T_ANSWER,T_EX, T_WRITER, T_LANG) VALUES ((select NVL(MAX(T_ID),0)+1 from TEST),2,'코딩문제4','문제','답','출력','j','JA');
INSERT INTO TEST (T_ID, USER_NUM, T_TITLE, T_CONTENT, T_ANSWER,T_EX, T_WRITER, T_LANG) VALUES ((select NVL(MAX(T_ID),0)+1 from TEST),2,'코딩문제5','문제','답','출력','j','JA');
select * from USERS

	-- TEST_REPLY 테이블
INSERT INTO TEST_REPLY (R_ID, T_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES ((select NVL(MAX(R_ID),0)+1 from TEST_REPLY),1,1,'댓글1','je',0);
INSERT INTO TEST_REPLY (R_ID, T_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES ((select NVL(MAX(R_ID),0)+1 from TEST_REPLY),1,1,'댓글1_대댓1','je',1);
INSERT INTO TEST_REPLY (R_ID, T_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES ((select NVL(MAX(R_ID),0)+1 from TEST_REPLY),2,1,'댓글2','je',0);
INSERT INTO TEST_REPLY (R_ID, T_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES ((select NVL(MAX(R_ID),0)+1 from TEST_REPLY),2,1,'댓글2_대댓1','je',1);

INSERT INTO TEST_REPLY (R_ID, T_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES ((select NVL(MAX(R_ID),0)+1 from TEST_REPLY),1,1,'댓글3','je',0);
INSERT INTO TEST_REPLY (R_ID, T_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES ((select NVL(MAX(R_ID),0)+1 from TEST_REPLY),1,1,'댓글4','je',0);
INSERT INTO TEST_REPLY (R_ID, T_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES ((select NVL(MAX(R_ID),0)+1 from TEST_REPLY),1,1,'댓글5','je',0);

-- -------------------------------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------------------------------
select * from TEST_REPLY

-- 삭제 유무 변경 
UPDATE TEST_REPLY SET DELETE_AT='Y' WHERE R_ID =1 AND PARENT_ID=0 AND USER_NUM=1;

update TEST set RE_CNT= RE_CNT+1 where T_ID=1;

-- 삭제된 댓글입니다. 문구출력   ==> CASE WHEN X=Y THEN A ELSE B END : X=Y가 TRUE면 A이고 그렇지 않으면 B다.
SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN '삭제된 댓글' ELSE R_CONTENT END FROM TEST_REPLY WHERE R_ID=1;
SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN '삭제된 댓글' ELSE R_CONTENT END, R_DATE, DELETE_AT, R_WRITER, PARENT_ID FROM TEST_REPLY;

SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN '삭제된 댓글' ELSE R_CONTENT END, R_DATE, DELETE_AT, R_WRITER, PARENT_ID FROM TEST_REPLY WHERE R_ID=1;
SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, R_DATE, DELETE_AT, R_CONTENT, PARENT_ID FROM TEST_REPLY WHERE R_ID=1;

SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, CASE WHEN DELETE_AT='Y' THEN '삭제된 댓글' ELSE R_CONTENT END, R_DATE, DELETE_AT, PARENT_ID FROM TEST_REPLY WHERE R_ID=1;

SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, CASE WHEN DELETE_AT='Y' THEN '삭제된 댓글' ELSE R_CONTENT END, R_DATE, DELETE_AT, PARENT_ID FROM TEST_REPLY WHERE R_ID=1;

-- 안됭 -> SELECT * FROM TEST_REPLY WHERE DELETE_AT= CASE WHEN DELETE_AT='Y' THEN '삭제된 댓글' ELSE R_CONTENT END;


-- 댓글, 대댓글 변경 
UPDATE TEST_REPLY SET R_CONTENT='댓글1 수정' WHERE R_ID=1 ;

UPDATE TEST_REPLY SET DELETE_AT='Y' WHERE R_ID =1 AND PARENT_ID=0 AND USER_NUM=1;


SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, CASE WHEN DELETE_AT='Y' THEN '*삭제된 댓글입니다.' ELSE R_CONTENT END, R_DATE, DELETE_AT, PARENT_ID FROM TEST_REPLY WHERE R_ID=?;

-- 댓글 SELECTALL 부분 
SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, CASE WHEN DELETE_AT='Y' THEN '*삭제된 댓글입니다.' ELSE R_CONTENT END, R_DATE, DELETE_AT, PARENT_ID FROM (SELECT * FROM TEST_REPLY WHERE USER_NUM=1 AND PARENT_ID=1 ORDER BY R_DATE DESC) WHERE ROWNUM>0 AND ROWNUM <= 5;


SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, CASE WHEN DELETE_AT='Y' THEN '*삭제된 댓글입니다.' ELSE R_CONTENT END, R_DATE, DELETE_AT, PARENT_ID FROM TEST_REPLY WHERE PARENT_ID=1 ORDER BY R_DATE DESC;


SELECT * FROM (SELECT ROWNUM AS RNUM, TEST_REPLY.* FROM TEST_REPLY WHERE USER_NUM=1 AND PARENT_ID=0 AND ROWNUM <= 5 ORDER BY R_DATE DESC) WHERE 3<=RNUM;

-- -------------------------------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------------------------------

DELETE FROM USERS WHERE USER_NUM=2;
DELETE FROM TEST WHERE T_ID =2;
DELETE FROM TEST_reply WHERE r_ID =1;


SELECT * FROM TEST_REPLY;
SELECT * FROM TEST_REPLY WHERE PARENT_ID=0 ORDER BY R_DATE DESC;
SELECT * FROM TEST_REPLY  ORDER BY r_ID asc;


update TEST set RE_CNT= RE_CNT+1 where T_ID=1;

SELECT * FROM TEST;


SELECT * FROM TEST_REPLY WHERE R_ID=1 AND PARENT_ID =0;

SELECT * FROM (SELECT * FROM TEST_REPLY ORDER BY R_DATE) WHERE USER_NUM=1 and PARENT_ID=0 AND ROWNUM>=2 and ROWNUM <=5;

SELECT * FROM (SELECT * FROM TEST_REPLY WHERE PARENT_ID=0 ORDER BY R_DATE desc) where ROWNUM>0 and ROWNUM <=5;

SELECT * FROM (SELECT * FROM TEST_REPLY WHERE USER_NUM=1 AND PARENT_ID=0 ORDER BY R_DATE DESC) WHERE ROWNUM>0 AND ROWNUM <= 5;

SELECT * FROM (SELECT ROWNUM AS RNUM, TEST.* FROM TEST WHERE ROWNUM<=3 ORDER BY T_DATE DESC) WHERE 2<=RNUM 
select * from (select a.*, rownum as rnum from(select * from TEST where T_TITLE LIKE '' order by T_DATE desc) a) where (rnum <=1 and rnum >=3)
SELECT * FROM TEST WHERE T_ID=1
select T_DATE from TEST

select * from (select a.*, rownum as rnum from(select * from TEST where T_TITLE LIKE '%%' order by t_date desc) a) where (rnum <=2 and rnum >=1)


select * from board
select * from (select a.*, rownum as rnum from(select * from TEST where T_TITLE LIKE '%%' order by t_date desc) a) where (rnum <=3 and rnum >=1)
select * from (select a.*, rownum as rnum from(select * from board where b_ctgr = 'board'  order by b_date desc) a) where (rnum <=3 and rnum >=1)
"select * from (select a.*, rownum as rnum from(select * from board where b_ctgr = ? and B_TITLE LIKE '%' || '' || '%' order by b_date desc) a) where (rnum <=2 and rnum >=3)


SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, 
                  R_CONTENT , R_DATE, DELETE_AT, 
                  PARENT_ID FROM (SELECT ROWNUM AS RNUM, TEST_REPLY.* FROM TEST_REPLY WHERE T_ID=1 AND PARENT_ID=0 AND ROWNUM<=7 
                  ORDER BY R_DATE DESC) WHERE 1<=RNUM
