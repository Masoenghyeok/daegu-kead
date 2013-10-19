create table infoStudent(
idx integer auto_increment primary key,
stdName varchar(20) not null,
juminNum char(14) not null,
startDate date not null,
endDate date,
mobile char(13),
tel char(13),
stdAddr varchar(40),
roomNum integer(3) not null,
stdType integer not null,
email varchar(40)
);

create table post(
	seq int(5) primary key,
	zipcode char(7),
	sido varchar(4),
	gugun varchar(17),
	dong varchar(52),
	bunji varchar(17)
);

delete from post;

update infoStudent set stdType = 0 where idx='1';
select * from infoStudent;

show variables like '%char%';

drop table infostudent;

select count(seq) as cnt from post;

select * from post where seq = 40000;

desc infostudent;

alter table infoStudent add column grade int after stdtype;

alter table infoStudent drop column grade;

alter table infoStudent change roornum roomNum int(3);

alter table infoStudent modify column startDate date;

alter table infoStudent modify column endDate date;

desc infoStudent;
