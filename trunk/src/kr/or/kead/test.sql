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

alter table infoStudent add column depart int after email;


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



update depart set name='전자정보', prof=0, tel='053-999-9999' where code=200;
select code from depart where name = "정보";

select max(code) from depart;

select * from depart;



create table professor (
	code int primary key,
	name varchar(20) not null,
	depart int,
	course varchar(30)
);

insert into professor values(1001, '이순재', 100, "자바프로그래밍");
insert into professor values(1002, '백일섭', 100, "데이터베이스");
insert into professor values(2001, '박근영', 200, "안드로이드");
insert into professor values(2002, '신구', 200, "비주얼베이직");

update depart set prof=1001 where code =100;
update depart set prof=2001 where code =200;

select * from professor;

select d.code, d.name, d.tel, p.code, p.name, p.course
from depart d, professor p where d.prof = p.code and d.name="정보";

select d.prof, d.name ,(select p.name, p.depart, p.course from professor where p.code = d.prof) as p
from depart d
where d.name="정보";

select p.name, p.depart, p.course from depart d, professor p,
 where p.code = d.prof;
 
 
 -- 학생을 추가 할때 학과 정보를 반드시 입력, 학과정보는 학과 테이블에 있는 
 -- 정보만 추가 
 
 -- infoStudent 에 depart필드 추가
 alter table infoStudent add column departCode int; 
 
 -- infoStudent 에 외래키 추가
 -- depart 테이블의 code가 변경되면 연쇄(cascade)적으로 infoStudent 테이블의
 -- departCode 도 변경 code 가 삭제되면 그대로 두는 조건
 alter table infoStudent add constraint infoStudent_departCode_fk
 foreign key (departCode) references depart(code)
 on update cascade
 on delete no action;
 
 alter table depart add constraint depart_prof_fk
 foreign key (prof) references professor(code)
 on update cascade
 on delete no action;
 
  alter table professor add constraint professor_depart_fk
 foreign key (depart) references depart(code)
 on update cascade
 on delete no action;

 
 -- 테스트(10/24)


