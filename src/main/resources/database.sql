drop table tbl_board;
CREATE TABLE tbl_board (
	no				INTEGER AUTO_INCREMENT PRIMARY KEY,
	title			VARCHAR(200) NOT NULL,
	content			TEXT,
	writer			VARCHAR(50) NOT NULL,
	REG_DATE		DATETIME DEFAULT CURRENT_TIMESTAMP,
	UPDATE_DATE		DATETIME DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO tbl_board(title, content, writer)
values
	('테스트 제목1', '테스트 내용1', 'user00'),
	('테스트 제목2', '테스트 내용2', 'user00'),
	('테스트 제목3', '테스트 내용3', 'user00'),
	('테스트 제목4', '테스트 내용4', 'user00'),
	('테스트 제목5', '테스트 내용5', 'user00');


select * from tbl_board;

select count(*) from tbl_board;



drop table if exists tbl_member;
create table tbl_member(
	username varchar(50) primary key,
	password varchar(128) not null,
	email varchar(50) not null,
	reg_date datetime default now(),
	update_date datetime default now()

);

drop table if exists tbl_member_auth;
create table tbl_member_auth(
	username varchar(50) not null,
	role varchar(50) not null,
 	primary key(username, role),
	constraint fk_authorities_users foreign key (username)
				references tbl_member(username)
);


select distinct writer from tbl_board
order by writer;

insert into tbl_member(username, password, email)
values
	('admin', '$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC', 'admin@galapgos.org'),
	('user0', '$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC', 'user00@galapgos.org'),
	('user1', '$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC', 'user00@galapgos.org'),
	('user2', '$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC', 'user00@galapgos.org'),
	('user3', '$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC', 'user00@galapgos.org'),
	('user4', '$2a$10$EsIMfxbJ6NuvwX7MDj4WqOYFzLU9U/lddCyn0nic5dFo3VfJYrXYC', 'user00@galapgos.org');


select * from tbl_member;

insert into tbl_member_auth(username, role)
values
	('admin','ROLE_ADMIN'),
	('admin','ROLE_MANAGER'),
	('admin','ROLE_MEMBER'),
	('user0','ROLE_MANAGER'),
	('user0','ROLE_MEMBER'),
	('user1','ROLE_MEMBER'),
	('user2','ROLE_MEMBER'),
	('user3','ROLE_MEMBER'),
	('user4','ROLE_MEMBER');

select * from tbl_member_auth order by role;


select m.username, password, email, reg_date, update_date, role
from
	tbl_member m left outer join tbl_member_auth a
			on m.username = a.username
where m.username = 'admin';


create table persistent_logins(
	series      varchar(64) primary key,
	username	varchar(64) not null,
	token 		varchar(64) not null,
	last_used   timestamp not null
);



create table tbl_board_attachment (
    no integer auto_increment primary key,
    filename varchar(256) not null,		-- 원본 파일 명
    path varchar(256) not null,			-- 서버에서의 파일 경로
    content_type varchar(56),				-- content-type
    size integer,								-- 파일의 크기
    bno integer not null,
    constraint foreign key references tbl_board(bno),
    reg_date datetime default now()
);



// 댓글 테이블
drop table if exists tbl_comment;

create table tbl_comment (
	no						integer auto_increment primary key,
	bno 					integer not null,					-- tbl_board에 대한 FK
	writer				varchar(50) not null,			-- tbl_member에 대한 FK
	content			varchar(2000) not null,
 	reg_date			datetime default now(),
	update_date	datetime default now(),

	constraint fk_comment_board foreign key(bno) references tbl_board(bno),
	constraint fk_comment_member foreign key(writer) references tbl_member(username)

);
