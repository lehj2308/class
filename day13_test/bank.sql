create table aBank(
   anum int primary key,
    aname varchar(20),
    abalance int
);

create table bBank(
   bnum int primary key,
    bname varchar(20),
    bbalance int
);

insert into aBank values(1001,'아무무',5000);
insert into bBank values(2001,'티모',1000);

drop table aBbank;
drop table bBank2;



