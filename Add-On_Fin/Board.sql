drop table users
drop table users cascade constraints
drop table board
drop table test cascade constraints

drop table board_reply
drop table test_reply
create table users(
   user_num int primary key,
   user_name varchar(15) not null,
   user_id varchar(50) not null,
   user_pw varchar(50) not null,
   user_hp varchar(25) not null,
   user_gender varchar(5) not null,
   user_email varchar(255) not null,
   user_addr varchar(255) not null,
   user_birth varchar(30) not null,
   icon_id varchar(30) not null

)
SELECT * FROM USERS;
INSERT INTO USERS VALUES (1, '김길동', 'kim', '1234', '01012341234', 'M', 'xxssgg120@naver.com', '경기도 군포시', '19960205', '1');
INSERT INTO USERS VALUES (2, '홍길동', 'hong', '1234', '01012345678', 'F', 'asdf1234@naver.com', '경기도 안양시', '19991021', '2');
/* 보드  */
create table board(
   b_id int primary key,
   user_num int not null,
   b_ctgr varchar(50) not null,
   b_title varchar(100) not null,
   b_content varchar(4000) not null,
   b_writer varchar(50) not null,
   b_date date default sysdate,
   b_hit int default 0,
   b_lang varchar(20) not null,
   b_count int default 0,
   re_cnt int default 0,
   constraint board_cons foreign key (user_num) references users(user_num) on delete cascade
);
select * from board;

INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'question','게시글1','게시글 내용1','kim','JAVA');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'question','게시글2','게시글 내용2','hong','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),1,'question','q&A1','what is JSP?','kim','JSP');
delete from board where b_id = 1;
select * from board_reply
delete from BOARD_REPLY where r_id=3 or r_id=4
create table board_reply(

   r_id int primary key,
   b_id int not null,
   user_num int not null,
   r_content varchar(255) not null,
   r_date date default sysdate,
   delete_at varchar(1) default 'n',
   r_writer varchar(20) not null,
   parent_id int not null,
   constraint b_id_cons foreign key (b_id) references board(b_id) on delete cascade,
   constraint user_num_cons2 foreign key (user_num) references users(user_num) on delete cascade
);

INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,2,'댓글1','hong',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'댓글2','kim',0);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'대댓글1','hong',1);
INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,1,'대댓글2','kim',1);


SELECT * FROM BOARD_REPLY;

SELECT * FROM (SELECT ROWNUM AS RNUM, BOARD_REPLY.* FROM BOARD_REPLY WHERE USER_NUM=1 AND PARENT_ID=1 AND ROWNUM <= 3 ORDER BY R_DATE DESC) WHERE 2<=RNUM;

SELECT * FROM (SELECT ROWNUM AS RNUM, BOARD.* FROM BOARD WHERE ROWNUM <= 10 and b_ctgr='announce' ORDER BY B_DATE DESC) WHERE 3<=RNUM;
select * from (select * from b_ctgr='announce' order by b_date desc) 
SELECT R_ID, B_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, CASE WHEN DELETE_AT='Y' THEN '*삭제된 댓글입니다.' ELSE R_CONTENT END, R_DATE, DELETE_AT, PARENT_ID FROM (SELECT ROWNUM AS RNUM, BOARD_REPLY.* FROM BOARD_REPLY WHERE USER_NUM=1 AND ROWNUM <=5 ORDER BY R_DATE DESC) BOARD_REPLY WHERE 0<=RNUM;

select * from
SELECT COUNT(*) FROM BOARD_REPLY WHERE PARENT_ID=1;
SELECT * FROM (SELECT ROWNUM AS RNUM, BOARD.* FROM BOARD WHERE ROWNUM<= 10 ORDER BY B_DATE DESC) WHERE 5 <=RNUM

SELECT * FROM (SELECT ROWNUM AS RNUM, BOARD.* FROM BOARD WHERE ROWNUM<= 10 ORDER BY B_DATE DESC) WHERE 5 <=RNUM
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'question','게시글3','게시글 내용2','hong','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'announce','게시글4','게시글 내용2','hong','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'announce','게시글5','게시글 내용2','hong','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'announce','게시글6','게시글 내용2','hong','C');
INSERT INTO BOARD(B_ID, USER_NUM, B_CTGR, B_TITLE, B_CONTENT, B_WRITER, B_LANG) VALUES((SELECT NVL(MAX(B_ID), 0)+1 FROM BOARD),2,'question','게시글7','게시글 내용2','hong','C');
SELECT * FROM (SELECT ROWNUM AS RNUM, BOARD_REPLY.* FROM BOARD WHERE  AND B_CTGR='announce' AND ROWNUM <= 3 ORDER BY R_DATE DESC) WHERE 2<=RNUM;


select * from board
select * from users
update board set b_ctgr = 'board' where b_ctgr='자유게시판'


SELECT * FROM (SELECT ROWNUM AS RNUM, BOARD.* FROM BOARD WHERE B_CTGR='announce' AND ROWNUM<=4 ORDER BY B_DATE desc) WHERE 2<=ROWNUM AND B_TITLE LIKE '%' || '' || '%'


select * from board_reply
SELECT R_ID, B_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, 
			CASE WHEN DELETE_AT='Y' THEN '*삭제된 댓글입니다.' ELSE R_CONTENT END, 
			R_DATE, DELETE_AT, 
			 PARENT_ID FROM (SELECT ROWNUM AS RNUM, BOARD_REPLY.* FROM BOARD_REPLY 
		WHERE ROWNUM <=3 ORDER BY R_DATE DESC) BOARD_REPLY WHERE 1<=RNUM
		
		
SELECT USER_PW FROM USERS WHERE USER_ID='kim'		

SELECT R_ID, B_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, 
          CASE WHEN DELETE_AT='Y' THEN '*삭제된 댓글입니다.' ELSE R_CONTENT END, 
         R_DATE, DELETE_AT, 
         PARENT_ID FROM (SELECT ROWNUM AS RNUM, BOARD_REPLY.* FROM BOARD_REPLY 
         WHERE B_ID=1 AND PARENT_ID=0 AND ROWNUM <=4 ORDER BY R_DATE DESC) BOARD_REPLY WHERE 1<=RNUM
         
select * from board_reply

SELECT R_ID, B_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, 
         R_CONTENT ,
         R_DATE, DELETE_AT, 
         PARENT_ID FROM (SELECT ROWNUM AS RNUM, BOARD_REPLY.* FROM BOARD_REPLY 
         WHERE B_ID=1 AND PARENT_ID=0 AND ROWNUM <=3 ORDER BY R_DATE DESC) BOARD_REPLY WHERE 1<=RNUM
         
                  
  INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,2,'댓글3','hong',0);
  INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,2,'댓글4','hong',0);
  INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,2,'댓글5','hong',0);
  INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,2,'댓글6','hong',0);
  INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,2,'댓글7','hong',0);
  INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,2,'댓글8','hong',0);
  INSERT INTO BOARD_REPLY (R_ID, B_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES((SELECT NVL(MAX(R_ID), 0)+1 FROM BOARD_REPLY),1,2,'댓글9','hong',0);
  
  alter table board rename column b_count to re_cnt
  SELECT COUNT(*) FROM BOARD_REPLY WHERE B_ID=14 AND PARENT_ID=0
  SELECT COUNT(*) FROM BOARD_REPLY WHERE B_ID=14 AND PARENT_ID=0
  select * from board
  UPDATE BOARD SET RE_CNT = RE_CNT-1 WHERE B_ID=0
  select * from board_reply
 SELECT R_ID, B_ID, USER_NUM, 
			CASE WHEN DELETE_AT='Y' THEN 'unknown' ELSE R_WRITER END AS R_WRITER, 
			CASE WHEN DELETE_AT='Y' THEN '*삭제된 댓글입니다.' ELSE R_CONTENT END AS R_CONTENT , 
			R_DATE, DELETE_AT, PARENT_ID FROM (
			SELECT ROWNUM AS RNUM, BOARD_REPLY.* FROM (
			SELECT * FROM BOARD_REPLY WHERE USER_NUM=1 AND DELETE_AT='N' ORDER BY R_DATE DESC
			) BOARD_REPLY WHERE ROWNUM<=6
			) WHERE RNUM > 3 ORDER BY R_DATE DESC
			
	select * from board		
update board_reply set delete_at = 'N';
SELECT * FROM (
	                     SELECT ROWNUM AS RNUM, BOARD.* FROM (
	                     SELECT * FROM BOARD WHERE USER_NUM=1 AND B_CTGR ='board' AND B_TITLE LIKE '%'||''||'%' ORDER BY B_DATE DESC
	                     ) BOARD WHERE ROWNUM <= 4
	                     ) WHERE RNUM > 0 ORDER BY '%' DESC;
	                    
select * from users
delete from users where user_num=4

	         SELECT * FROM (
	                     SELECT ROWNUM AS RNUM, BOARD.* FROM (
	                     SELECT * FROM BOARD WHERE USER_NUM=1 AND B_TITLE LIKE '%'||''||'%' AND B_CTGR ='board' ORDER BY RE_CNT DESC, B_DATE DESC
	                     ) BOARD WHERE ROWNUM <= 4
	                     ) WHERE RNUM > 0 ORDER BY RE_CNT DESC