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

-- �ڵ� �׽�Ʈ ��� ���̺�
CREATE TABLE TEST_REPLY(
	R_ID INT PRIMARY KEY, -- ��� ��ȣ
	T_ID INT NOT NULL, -- TEST�Խñ� ��ȣ
	USER_NUM INT NOT NULL, -- ȸ����ȣ
	R_CONTENT VARCHAR(225) NOT NULL, -- ���/���� ���� 
	R_DATE DATE DEFAULT SYSDATE, -- ���/���� �ۼ���
	DELETE_AT VARCHAR(1) DEFAULT 'N', -- ��� ���� ����
	R_WRITER VARCHAR(20) NOT NULL, -- �ۼ��� == ID
	PARENT_ID INT NOT NULL, -- ���� Ȯ��
	constraint t_id_cons foreign key (t_id) references test(t_id) on delete cascade,
	CONSTRAINT user_num_cons3 FOREIGN KEY (USER_NUM) REFERENCES users(user_num) on delete cascade
)
-- -------------------------------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------------------------------
-- �� insert �� => �ʿ��Ͻø� �̿��ϼ���.
	-- USERS ���̺�
insert into USERS values((select NVL(MAX(user_num),0)+1 from users), '������', 'je','111','01011111111','F','je@gmail.com','�ּ�','19960927','1');
insert into USERS values((select NVL(MAX(user_num),0)+1 from users), 'GG', 'GG','111','01011111111','F','����@gmail.com','GG�ּ�','20210914','2');

	-- TEST ���̺�
INSERT INTO TEST (T_ID, USER_NUM, T_TITLE, T_CONTENT, T_ANSWER,T_EX, T_WRITER, T_LANG) VALUES ((select NVL(MAX(T_ID),0)+1 from TEST),1,'�ڵ�����1','��������1','��1','���1','je','JAVA');
INSERT INTO TEST (T_ID, USER_NUM, T_TITLE, T_CONTENT, T_ANSWER,T_EX, T_WRITER, T_LANG) VALUES ((select NVL(MAX(T_ID),0)+1 from TEST),2,'�ڵ�����2','����','��','���','j','JA');
INSERT INTO TEST (T_ID, USER_NUM, T_TITLE, T_CONTENT, T_ANSWER,T_EX, T_WRITER, T_LANG) VALUES ((select NVL(MAX(T_ID),0)+1 from TEST),2,'�ڵ�����3','����','��','���','j','JA');
INSERT INTO TEST (T_ID, USER_NUM, T_TITLE, T_CONTENT, T_ANSWER,T_EX, T_WRITER, T_LANG) VALUES ((select NVL(MAX(T_ID),0)+1 from TEST),2,'�ڵ�����4','����','��','���','j','JA');
INSERT INTO TEST (T_ID, USER_NUM, T_TITLE, T_CONTENT, T_ANSWER,T_EX, T_WRITER, T_LANG) VALUES ((select NVL(MAX(T_ID),0)+1 from TEST),2,'�ڵ�����5','����','��','���','j','JA');
select * from USERS

	-- TEST_REPLY ���̺�
INSERT INTO TEST_REPLY (R_ID, T_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES ((select NVL(MAX(R_ID),0)+1 from TEST_REPLY),1,1,'���1','je',0);
INSERT INTO TEST_REPLY (R_ID, T_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES ((select NVL(MAX(R_ID),0)+1 from TEST_REPLY),1,1,'���1_���1','je',1);
INSERT INTO TEST_REPLY (R_ID, T_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES ((select NVL(MAX(R_ID),0)+1 from TEST_REPLY),2,1,'���2','je',0);
INSERT INTO TEST_REPLY (R_ID, T_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES ((select NVL(MAX(R_ID),0)+1 from TEST_REPLY),2,1,'���2_���1','je',1);

INSERT INTO TEST_REPLY (R_ID, T_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES ((select NVL(MAX(R_ID),0)+1 from TEST_REPLY),1,1,'���3','je',0);
INSERT INTO TEST_REPLY (R_ID, T_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES ((select NVL(MAX(R_ID),0)+1 from TEST_REPLY),1,1,'���4','je',0);
INSERT INTO TEST_REPLY (R_ID, T_ID, USER_NUM, R_CONTENT, R_WRITER, PARENT_ID) VALUES ((select NVL(MAX(R_ID),0)+1 from TEST_REPLY),1,1,'���5','je',0);

-- -------------------------------------------------------------------------------------------------------
-- -------------------------------------------------------------------------------------------------------
select * from TEST_REPLY

-- ���� ���� ���� 
UPDATE TEST_REPLY SET DELETE_AT='Y' WHERE R_ID =1 AND PARENT_ID=0 AND USER_NUM=1;

update TEST set RE_CNT= RE_CNT+1 where T_ID=1;

-- ������ ����Դϴ�. �������   ==> CASE WHEN X=Y THEN A ELSE B END : X=Y�� TRUE�� A�̰� �׷��� ������ B��.
SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN '������ ���' ELSE R_CONTENT END FROM TEST_REPLY WHERE R_ID=1;
SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN '������ ���' ELSE R_CONTENT END, R_DATE, DELETE_AT, R_WRITER, PARENT_ID FROM TEST_REPLY;

SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN '������ ���' ELSE R_CONTENT END, R_DATE, DELETE_AT, R_WRITER, PARENT_ID FROM TEST_REPLY WHERE R_ID=1;
SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, R_DATE, DELETE_AT, R_CONTENT, PARENT_ID FROM TEST_REPLY WHERE R_ID=1;

SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, CASE WHEN DELETE_AT='Y' THEN '������ ���' ELSE R_CONTENT END, R_DATE, DELETE_AT, PARENT_ID FROM TEST_REPLY WHERE R_ID=1;

SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, CASE WHEN DELETE_AT='Y' THEN '������ ���' ELSE R_CONTENT END, R_DATE, DELETE_AT, PARENT_ID FROM TEST_REPLY WHERE R_ID=1;

-- �ȉ� -> SELECT * FROM TEST_REPLY WHERE DELETE_AT= CASE WHEN DELETE_AT='Y' THEN '������ ���' ELSE R_CONTENT END;


-- ���, ���� ���� 
UPDATE TEST_REPLY SET R_CONTENT='���1 ����' WHERE R_ID=1 ;

UPDATE TEST_REPLY SET DELETE_AT='Y' WHERE R_ID =1 AND PARENT_ID=0 AND USER_NUM=1;


SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, CASE WHEN DELETE_AT='Y' THEN '*������ ����Դϴ�.' ELSE R_CONTENT END, R_DATE, DELETE_AT, PARENT_ID FROM TEST_REPLY WHERE R_ID=?;

-- ��� SELECTALL �κ� 
SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, CASE WHEN DELETE_AT='Y' THEN '*������ ����Դϴ�.' ELSE R_CONTENT END, R_DATE, DELETE_AT, PARENT_ID FROM (SELECT * FROM TEST_REPLY WHERE USER_NUM=1 AND PARENT_ID=1 ORDER BY R_DATE DESC) WHERE ROWNUM>0 AND ROWNUM <= 5;


SELECT R_ID, T_ID, USER_NUM, CASE WHEN DELETE_AT='Y' THEN 'UNKNOWN' ELSE R_WRITER END, CASE WHEN DELETE_AT='Y' THEN '*������ ����Դϴ�.' ELSE R_CONTENT END, R_DATE, DELETE_AT, PARENT_ID FROM TEST_REPLY WHERE PARENT_ID=1 ORDER BY R_DATE DESC;


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
