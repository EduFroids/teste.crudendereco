create table Address (
	id bigint not null auto_increment,
	street_name varchar(255) not null,
	address_number varchar(6) not null,
	complement varchar(255),
	neighbourhood varchar(100) not null,
	city varchar(100) not null,
	state varchar(2) not null,
	country varchar(100) not null,
	zipcode varchar(9) not null,
	latitude varchar(20),
	longitude varchar(20),
	
	primary key (id)
) engine=InnoDB default charset=utf8;