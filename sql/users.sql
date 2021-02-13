create table users
(
	login varchar,
	email varchar,
	password varchar,
	role int default 0,
	id int auto_increment,
	constraint USERS_PK
		primary key (id)
);