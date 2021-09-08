create table coffee(
	num int primary key,
	name varchar(30) not null,
	price int not null,
	information varchar(100) default '설명이 없어요'
);

insert into coffee values(1,'아메리카노', 3000, '에스프레소에 물을 넣어 연라게 먹는 커피');
insert into coffee (num,name,price)values(2,'카라멜 마끼아또', 4500);
insert into coffee values(3,'카페 라떼', 4000, '에스프레소에 우유를 곁들인 커피');


drop table coffee;
select * from coffee;