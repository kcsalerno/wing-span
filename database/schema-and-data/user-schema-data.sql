drop database if exists wing_span;
create database wing_span;

use wing_span;


-- -----------------------------------------------------
-- Table: sighting (user related table)
-- -----------------------------------------------------
create table sighting (
	user_id int not null,
    bird_id int not null,
    sighting_date date not null,
    city varchar(50) not null,
    state varchar(50) null,
    daytime boolean null,
    constraint pk_bird_trait
		primary key(user_id, bird_id),
    constraint fk_sighting_user_id
		foreign key (user_id)
		references `user`(user_id),
	 constraint fk_sighting_bird_id
		foreign key (bird_id)
		references bird(bird_id)
);