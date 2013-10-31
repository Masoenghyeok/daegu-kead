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

update infostudent set departCode='200' where idx ='2';

insert into depart values (100, "정보", null, "053-111-1111");
insert into depart values (200, "전자", null, "053-222-2222");
insert into depart values (300, "기계", null, "053-333-3333");
insert into depart values (400, "디자인", null, "053-444-4444");
insert into depart values (500, "경영회계", null, "053-555-5555");
insert into depart values (600, "경영회계", 2002, "02-1234-2132");



update depart set name='정보기술', prof=1001, tel='053-999-9999' where code=100;
update depart set name='전자', prof=2001, tel='053-999-9999' where code=200;
update depart set name='컴퓨터응용기계', prof=3001, tel='053-999-9999' where code=300;
update depart set name='디자인', prof=4001, tel='053-999-9999' where code=400;
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
insert into professor values(3001, '박건형', 300, "기계제도");
insert into professor values(3002, '최불암', 300, "재료역학");
insert into professor values(4001, '앙드레김', 400, "한복");
insert into professor values(4002, '샤넬', 400, "양장");
insert into professor values(4001, '앙드레김', 400, "한복");
insert into professor values(4002, '샤넬', 400, "양장");

update professor set course="회로설계" where code = 2001;
update professor set course="마이크로 프로세서" where code = 2002;

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
 
 -- 외래키 삭제 foreign key 이름이 없으면 제가 할 수가 없다
 alter table depart drop foreign key depart_prof_fk;
 
 insert into depart values (700, "텔레마케팅", 7001, "053-1234-2132");
 
  alter table professor add constraint professor_depart_fk
 foreign key (depart) references depart(code)
 on update cascade
 on delete no action;
 -- 리팩토링 sgjp
 
 -- 테스트(10/24)
 -- 학과테이블 삽입시 교수테이블이 비어 있다면 prof 필드에는 null값이 들어 가도록 한다.
static final String[] type={"지체장애","뇌병변장애","시각장애","청각장애",
		"언어장애", "안면장애",
		"신장장애","심장장애","간장애","호흡기장애",
		"장루,요루장애","간질장애",
		"지적장애","자폐성장애","정신장애"};
	static final int[] typeValue={		
		111,112,113,114,115,116,
		121,122,123,124,125,126,
		211,212,
		221
	};;
 
 
 create table handicap (
 	code int primary key,
 	name varchar(10) not null
 );
 
 select * from handicap;
 
 insert into handicap values(111, '지체장애');
 insert into handicap values(112, '뇌병변장애');
 insert into handicap values(113, '시각장애');
 insert into handicap values(114, '청각장애');
 insert into handicap values(115, '언어장애');
 insert into handicap values(116, '안면장애');
 insert into handicap values(121, '신장장애');
 insert into handicap values(122, '심장장애');
 insert into handicap values(123, '간장애');
 insert into handicap values(124, '호흡기장애');
 insert into handicap values(125, '장루,요루장애');
 insert into handicap values(126, '간질장애');
 insert into handicap values(211, '지적장애');
 insert into handicap values(212, '자폐성장애');
 insert into handicap values(221, '정신장애');
 alter table infoStudent change roornum roomNum int(3);
 
 alter table infoStudent change stdAddr stdAddr varchar(60);

