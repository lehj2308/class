drop table users;
select * from all_tables;

create table users(
	unum varchar(15) primary key,
	name varchar(15),
	passwd varchar(10),
	udate date default sysdate
);

create table message(
	mnum int primary key,
    unum varchar(15),
    msg varchar(100),
    favcount int default 0,
    replycount int default 0,
    mdate date default sysdate
);

create table reply(
   	rnum int primary key,
    mnum int,
    unum varchar(15),
    rdate date default sysdate,
    rmsg varchar(50),
    constraint msgrp foreign key (mnum) references message (mnum) on delete cascade
);

select * from message order by mdate desc;
select * from reply where mnum=1 and rownum <= 3 order by rdate desc

insert into users values('timo','Æ¼¸ð','1234',sysdate);
insert into message values(1,'timo','±ÛÀÛ¼º',1,2,sysdate);
insert into reply values(1,1,'timo',sysdate,'´ñ±Û1');
insert into reply values(2,1,'timo',sysdate,'´ñ±Û2');
insert into message values(2,'timo','¹ö¼¸',2,3,sysdate);
insert into reply values(3,2,'timo',sysdate,'´ñ±Û1');
insert into reply values(4,2,'timo',sysdate,'´ñ±Û2');
insert into reply values(5,2,'timo',sysdate,'´ñ±Û3');
insert into message values(3,'timo','È®ÀÎ',2,0,sysdate);

select * from reply;
select * from users;