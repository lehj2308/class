create table member(
	userNum int primary key,
	userID varchar(10) UNIQUE,
	userPW varchar(10) not null
);

select * from member;
drop table member;
insert into MEMBER values(1,'lee','1234');