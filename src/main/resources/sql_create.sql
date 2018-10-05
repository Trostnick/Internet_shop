drop database if exists demo;

create database demo;
use demo;

create table place_type (
	id				int 	primary key 	not null,
	name 			varchar(30) 			not null 
);


create table place(
	id				int 	primary key 	not null,
	name 			varchar(30) 			not null,
	info			text,
	adress			text,
	type_id			int
		references place_type(id)
		on update cascade
		on delete set null
);

create table camp_type(
	id				int 	primary key 	not null,
	name 			varchar(30) 			not null
);

create table camp(
	id				int 	primary key 	not null,
	name 			varchar(30) 			not null ,
	date_start		date					not null,
	date_finish		date					not null,
	age_min			int						not null,
	age_max			int						not null,
	children_count	int						not null,
	info			text,
	icon			blob,
	type_id			int
		references camp_type(id)
		on update cascade
		on delete set null,
	place_id		int
		references 	place(id)
		on update 	cascade
		on delete	set null
);


create table camp_photo(
	id				int 	primary key 	not null,
	image			varchar(80)				not null,
	camp_id			int						not null
		references	camp(id)
		on update 	cascade
		on delete	cascade
);
	
create table user_status(
	id				int 	primary key 	not null,
	name 			varchar(30) 			not null 
);

insert into user_status values
(	1, 	'client'),
(	2,	'manager'),
(	3, 	'admin'),
(	4,	'removed')
;


create table user(
	id				int 	primary key		not null,
	name 			varchar(50),	
	login 			varchar(20)				not null,
	password		varchar(100)			not null,
	status_id		int 		 			not null
		references 	user_status(id)  
		on update 	cascade
);


create table order_status(
	id				int 	primary key		not null,
	name			varchar(30)				not null
)
;

insert into order_status values
(	1,	'in basket'),
(	2,	'booked'),
(	3,	'payed'),
(	4,	'delivered'),
(	5,	'finished'),
(	6,	'removed')
;

create table buy_order(
	id				int primary key			not null,
	user_id			int						not null
		references 	user(id)
		on update 	cascade
		on delete 	set null,
	status_id		int						not null
		references 	order_status(id)
		on update 	cascade
);
	
	
create table order_camp_id(
	id				int 	primary key 	not null,
	count			int		default 1		not null,						
	order_id		int						not null
		references 	buy_order(id)			
		on update	cascade
		on delete	cascade,
	camp_id			int						not null
		references	camp(id)
		on update 	cascade
		on delete	cascade
);

commit;