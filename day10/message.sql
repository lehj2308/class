create table message(
	mnum int primary key,
	title varchar(20) not null,
	content varchar(100) not null,
	writer varchar(10) not null,
	wdate date default sysdate
)

insert into message values(1,'�ȳ��ϼ���','�ݰ����ϴ�','lee',sysdate);
insert into message values((select nvl(max(mnum),0)+1 from message),'�ȳ��ϼ���','�ݰ����ϴ�','lee',sysdate)

select * from message;
drop table message;