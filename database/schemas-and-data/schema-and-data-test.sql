drop database if exists wing_span_test;
create database wing_span_test;
use wing_span_test;

-- -----------------------------------------------------
-- Table: app_user
-- -----------------------------------------------------
create table app_user (
	app_user_id int primary key auto_increment,
    username varchar(50) not null unique,
    password_hash varchar(2048) not null,
    enabled boolean not null default(true),
    email varchar(255) not null-- ,
--     user_first_name varchar(50) null,
--     user_last_name varchar(50) null
);

-- -----------------------------------------------------
-- Table: user_role
-- -----------------------------------------------------
create table app_role (
	app_role_id int primary key auto_increment,
    `name` varchar(50) not null unique
);

-- -----------------------------------------------------
-- Table: app_user_role
-- -----------------------------------------------------
create table app_user_role (
	app_user_id int not null,
    app_role_id int not null,
    constraint pk_app_user_role
		primary key (app_user_id, app_role_id),
	constraint fk_app_user_role_user_id
		foreign key (app_user_id)
        references app_user(app_user_id),
	constraint fk_app_user_role_role_id
		foreign key	(app_role_id)
        references app_role(app_role_id)
);

-- -----------------------------------------------------
-- Table: badge (user related table)
-- -----------------------------------------------------
create table badge (
	badge_id int primary key auto_increment,
    badge_name varchar(50) not null,
    badge_description varchar(50) not null,
	badge_img_url varchar(512) not null
);

-- -----------------------------------------------------
-- Table: user_badge
-- -----------------------------------------------------
create table user_badge (
	app_user_id int not null,
    badge_id int not null,
    constraint pk_user_badge
		primary key (app_user_id, badge_id),
	constraint fk_user_badge_user_id
		foreign key (app_user_id)
		references app_user(app_user_id),
	 constraint fk_user_badge_badge_id
		foreign key (badge_id)
		references badge(badge_id)
);

-- -----------------------------------------------------
-- Table: avatar
-- -----------------------------------------------------
create table avatar (
	avatar_id int primary key auto_increment,
	avatar_img_url varchar(512) not null default 'https://media.istockphoto.com/photos/friends-using-binoculars-in-a-forest-picture-id1356557792?b=1&k=20&m=1356557792&s=170667a&w=0&h=5qzcB3V-uAA9hqIzfZqV4z2pUJ-bopoCUFqzwpE6v6o=',
	avatar_description varchar (100) null
);
  
-- -----------------------------------------------------
-- Table: user_avatar
-- -----------------------------------------------------
create table user_avatar (
	app_user_id int not null,
	avatar_id int not null,
	constraint pk_user_avatar
		primary key (app_user_id, avatar_id),
	constraint fk_user_avatar_user_id
		foreign key (app_user_id)
		references app_user(app_user_id),
	constraint fk_user_avatar_avatar_id
		foreign key (avatar_id)
		references avatar(avatar_id)   
);

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
    img_url varchar(512) not null
);

-- -----------------------------------------------------
-- Table: sighting (user related table)
-- -----------------------------------------------------
create table sighting (
	sighting_id int primary key auto_increment,
	app_user_id int not null,
    bird_id int not null,
    sighting_date date not null,
    city varchar(50) not null,
    state varchar(50) null,
    daytime boolean null,
    constraint fk_sighting_user_id
		foreign key (app_user_id)
		references app_user(app_user_id),
	 constraint fk_sighting_bird_id
		foreign key (bird_id)
		references bird(bird_id)
);

-- -----------------------------------------------------
-- Table: sighting_trait
-- -----------------------------------------------------
create table sighting_trait (
	sighting_id int not null,
    trait_id int not null,
    constraint pk_sighting_trait
		primary key(sighting_id, trait_id),
	constraint fk_sighting_trait_sighting_id
		foreign key(sighting_id)
        references sighting(sighting_id),
	constraint fk_sighting_trait_trait_id
		foreign key (trait_id)
        references trait(trait_id)
);

-- -----------------------------------------------------
-- Known Good State
-- -----------------------------------------------------
delimiter //

create procedure set_known_good_state()
begin

	delete from sighting_trait;
    delete from trait;
    alter table trait auto_increment = 1;
	delete from sighting;
	alter table sighting auto_increment = 1;
    delete from app_user_role;
    delete from user_avatar;
    delete from user_badge;
    delete from bird;
    alter table bird auto_increment = 1;
    delete from badge;
    alter table badge auto_increment = 1;
    delete from avatar;
    alter table avatar auto_increment = 1;
    delete from app_role;
	alter table app_role auto_increment = 1;
    delete from app_user;
	alter table app_user auto_increment = 1;

    -- -----------------------------------------------------
	-- Data
	-- -----------------------------------------------------
    
    insert into app_role (`name`) values
		('USER'),
		('ADMIN');

	-- Initial data to get started, passwords are set to "P@ssw0rd!" for now
-- 	insert into app_user (username, password_hash, email, user_first_name, user_last_name) values
    insert into app_user (username, password_hash, email) values
-- 		('admin1', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'admin@admin.com', 'Test', 'McTesterson'),
-- 		('user1', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'user@user.com', 'User', 'McUser');
		('admin1', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'admin@admin.com'),
		('user1', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'user@user.com');

	-- Initial app users with roles; account 1 is an Admin, account 2 is a User.
	insert into app_user_role (app_user_id, app_role_id) values 
		(1, 2),
		(2, 1);

	insert into badge (badge_name, badge_description, badge_img_url) values
		('5 Sightings', "You've made 5 sightings!", 'https://static.thenounproject.com/png/1120113-200.png'),
		('10 Sightings', "You've made 10 sightings!", 'https://static.thenounproject.com/png/1188264-200.png');
        
	insert into avatar (avatar_img_url, avatar_description) values
		('https://cdn.pixabay.com/photo/2017/05/25/21/26/bird-feeder-2344414__480.jpg', "Birdhouse"),
		('https://cdn.pixabay.com/photo/2022/09/29/08/39/european-robin-7486889__480.jpg', "Birdbath");
        
	insert into user_avatar (app_user_id, avatar_id) values
		(1, 1),
		(2, 2);

	insert into bird (common_name, scientific_name, img_url) values
		('Woodpecker', 'Picidae', 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Woodpecker_20040529_151837_1c_cropped.JPG/330px-Woodpecker_20040529_151837_1c_cropped.JPG'),
		('Pigeon', 'Columbidae', 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Treron_vernans_male_-_Kent_Ridge_Park.jpg/330px-Treron_vernans_male_-_Kent_Ridge_Park.jpg'),
		('Peacock', 'Pavo Cristatus', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Peacock_Plumage.jpg/330px-Peacock_Plumage.jpg'),
		('Turkey Vulture', 'Cathartes Aura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Urubu_a_tete_rouge_-_Turkey_Vulture.jpg/330px-Urubu_a_tete_rouge_-_Turkey_Vulture.jpg'),
		('Tree Swallow', 'Tachycineta Bicolor', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Tree_swallow_in_JBWR_%2825579%29.jpg/330px-Tree_swallow_in_JBWR_%2825579%29.jpg');

	insert into trait (`name`) values
		('Perching'),
		('Nesting'),
		('Hopping'),
		('Walking'),
		('Flying'),
		('Grooming'),
		('Feeding'),
		('Hunting'),
		('Singing');
    
    insert into sighting (app_user_id, bird_id, sighting_date, city, state, daytime) values
		(1, 1, '2020-10-01', 'Test City', 'Test State', true),
        (1, 2, '2021-11-01', 'Test City', 'Test State', true),
        (1, 3, '2021-12-01', 'Test City', 'Test State', false),
        (2, 4, '2022-04-01', 'Test City', 'Test State', false),
        (2, 5, '2022-06-01', 'Test City', 'Test State', true);
        
	insert into sighting_trait (sighting_id, trait_id) values
		(1, 1),
        (1, 2),
        (1, 3),
        (3, 4),
        (2, 5),
        (2, 6),
        (2, 7),
        (3, 8),
        (3, 9),
        (4, 1),
        (4, 2),
        (4, 3),
        (5, 5),
        (5, 7),
        (5, 9);
        
	insert into user_badge (app_user_id, badge_id) values
		(1, 1);

end //

delimiter ;
