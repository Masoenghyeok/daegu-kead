create table infoStudent(
idx integer auto_increment primary key,
stdName varchar(20) not null,
juminNum char(14) not null,
startDate char(10) not null,
endDate char(10),
mobile char(13),
tel char(13),
stdAddr varchar(40),
roorNum integer(3) not null,
stdType integer not null,
email varchar(40)
);

--insert into infoStudent values(null, '±Ë≈¬»Ò','123456-1234567','2013/10/02','2013/11/02','010-1234-5678'
--,'053-1234-5678','¥Î±∏ ¥ﬁº≠±∏',200,0,'abcd@abc.com');

update infoStudent set stdType = 0 where idx='1';
select * from infoStudent;

show variables like '%char%';

drop table infostudent;

desc infostudent;

alter table infoStudent add column grade int after stdtype;

alter table infoStudent drop column grade;

alter table infoStudent change roornum roomNum int(3);

alter table infoStudent modify column startDate date;

alter table infoStudent modify column endDate date;

desc infoStudent;
