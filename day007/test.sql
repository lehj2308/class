select * from all_tables;

create table tBoard(
	id int primary key,
	title varchar(30),
	writer varchar(15),
	content varchar(100),
	wdate date default sysdate
);
create table tMember(
	id varchar(15) primary key,
	password varchar(10),
	name varchar(15),
	role varchar(15)
);

insert into tMember values ('kim','1234','±è¾¾','USER');
insert into tMember values ('admin','1234','°ü¸®ÀÚ','ADMIN');
select * from tMember;
insert into tBoard (id,title,writer,content) values(1,'Á¦¸ñ','±è¾¾','±Û ³»¿ë');
select * from tBoard;