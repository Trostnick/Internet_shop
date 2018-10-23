drop database if exists demo;

create database demo;
use demo;

#create table place_type (
#	id				int 	primary key 	not null 	unique 	auto_increment,
#	name 			varchar(30) 			not null 
#);


create table place(
	id				int 	primary key 	not null 	unique 	auto_increment,
	name 			varchar(30) 			not null,
	info			text,
	address			text,
    removed 		bool	default FALSE
#	type_id			int
#		references place_type(id)
#		on update cascade
#		on delete set null
);

create table camp_type(
	id				int 	primary key 	not null 	unique 	auto_increment,
	name 			varchar(50) 			not null
);

insert into camp_type values
(	1,	'removed'),
(	2, 	'stationary'),
(	3,	'tent'),
(	4, 	'day stay')
;

create table camp(
	id				int 	primary key 	not null 	unique 	auto_increment,
	name 			varchar(30) 			not null,
	date_start		date					not null,
	date_finish		date					not null,
	age_min			int						not null,
	age_max			int						not null,
	children_count	int						not null,
    info			text,
	
	type_id			int						not null
		references 	camp_type(id)
		on update 	cascade
		on delete 	set null,
	place_id		int
		references 	place(id)
		on update 	cascade
		on delete	set null,
	icon			blob	
);


create table camp_photo(
	id				int 	primary key 	not null 	unique 	auto_increment,
	image			varchar(80)				not null,
	removed 		bool	default FALSE,
    camp_id			int						not null
		references	camp(id)
		on update 	cascade
		on delete	cascade
);
	
create table user_status(
	id				int 	primary key 	not null 	unique 	auto_increment,
	name 			varchar(30) 			not null 
);

insert into user_status values
(	1,	'removed'),
(	2, 	'client'),
(	3,	'manager'),
(	4, 	'admin')
;


create table user(
	id				int 	primary key		not null 	unique 	auto_increment,
	name 			varchar(50),	
	login 			varchar(20)				not null,
	password		varchar(100)			not null,
    status_id		int 		 			not null	default 2
		references 	user_status(id)  
		on update 	cascade
);


create table order_status(
	id				int 	primary key		not null 	unique 	auto_increment,
	name			varchar(30)				not null
)
;

insert into order_status values
(	1,	'removed'),
(	2,	'in basket'),
(	3,	'booked'),
(	4,	'payed'),
(	5,	'delivered'),
(	6,	'finished')
;

create table buy_order(
	id				int primary key			not null 	unique 	auto_increment,
    user_id			int						not null
		references 	user(id)
		on update 	cascade
		on delete 	set null,
	status_id		int						not null
		references 	order_status(id)
		on update 	cascade
);
	
	
create table order_camp_id(
	id				int 	primary key 	not null 	unique 	auto_increment,
	count			int		default 1		not null,
    removed 		bool	default FALSE,
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