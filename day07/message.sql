create table message(
	mnum int primary key,
	userID varchar(15) not null,
	title varchar(15) not null,
	content varchar(100) not null,
	wdate date default sysdate,
);

select * from message where writer='lee' order by mnum desc ;
drop table message;
select * from message;
insert into message values(3,'kim','제목','내용',sysdate);
insert into message values(4,'kim2','제목2','내용2',sysdate);
insert into message (mnum,title,content,writer,wdate) values((select nvl(max(mnum),0)+1 from message),'kim2','제목2','내용2',sysdate);
/* 샘플데이터 : 일반적으로 서비스 될때에는 샘플데이터 有! */
