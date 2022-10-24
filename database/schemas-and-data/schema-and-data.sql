-- -----------------------------------------------------
-- Note: This file must be run first.
-- -----------------------------------------------------
drop database if exists wing_span;
create database wing_span;

use wing_span;

-- -----------------------------------------------------
-- Table: trait
-- -----------------------------------------------------
create table trait (
	trait_id int primary key auto_increment,
    `name` varchar(50) not null
);

-- -----------------------------------------------------
-- Table: bird
-- -----------------------------------------------------
create table bird  (
	bird_id int primary key auto_increment,
	common_name varchar(50) not null,
    scientific_name varchar(50) not null,
    image_url varchar(512) not null default 'https://images.unsplash.com/photo-1444464666168-49d633b86797?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8YmlyZHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60'
);
  
-- -----------------------------------------------------
-- Table: bird_trait
-- -----------------------------------------------------
create table bird_trait (
	bird_id int not null,
    trait_id int not null,
    constraint pk_bird_trait
		primary key(bird_id, trait_id),
	constraint fk_bird_trait_bird_id
		foreign key(bird_id)
        references bird(bird_id),
	constraint fk_bird_trait_trait_id
		foreign key (trait_id)
        references trait(trait_id)
);