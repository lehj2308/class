create table coffee(
	num int primary key,
	name varchar(30) not null,
	price int not null,
	information varchar(100) default '������ �����'
);

insert into coffee values(1,'�Ƹ޸�ī��', 3000, '���������ҿ� ���� �־� ����� �Դ� Ŀ��');
insert into coffee (num,name,price)values(2,'ī��� �����ƶ�', 4500);
insert into coffee values(3,'ī�� ��', 4000, '���������ҿ� ������ ����� Ŀ��');


drop table coffee;
select * from coffee;