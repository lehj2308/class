create table member(
	unum int primary key,
	userID varchar(10) UNIQUE,
	userPW varchar(20) not null
)

insert into member values(1,'lee','1234');

drop table member;
select * from member;
update member set userPW='4321' where userID='lee'