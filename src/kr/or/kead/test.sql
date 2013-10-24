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

select * from post where dong like '%송현동%'

drop table infostudent;

select count(seq) as cnt from post;

insert into infoStudent values(null,'wfwefew','fdgfdgdfg','2013-11-19','2014-12-18','010-1234-1231','124124','dfgdgd',222,111,1,'fdgdfgfdg');

select * from post where seq = 40000;

desc infostudent;

alter table infoStudent add column grade int after stdtype;

alter table infoStudent drop column grade;

alter table infoStudent change roornum roomNum int(3);

alter table infoStudent modify column startDate date;

alter table infoStudent modify column endDate date;

desc infoStudent;


-- 컴퓨터응용기계분야 전자분야 정보기술분야 디자인분야 의상분야 경영회계분야 텔레마케팅 
-- 시각특화훈련분야 청각특화훈련분야 지적특화훈련분야
-- 맞춤훈련

create table depart(
	code int primary key,
	name varchar(20) not null,
	prof int,
	tel char(13) not null	
);

insert into depart values (100, "정보", null, "053-111-1111");
insert into depart values (200, "전자", null, "053-222-2222");
insert into depart values (300, "기계", null, "053-333-3333");
insert into depart values (400, "디자인", null, "053-444-4444");



select * from depart;

