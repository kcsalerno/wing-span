use wing_span;

drop table if exists app_user_role;
drop table if exists app_role;
drop table if exists sighting_trait;
drop table if exists sighting;
drop table if exists user_badge;
drop table if exists badge;
drop table if exists user_avatar;
drop table if exists avatar;
drop table if exists app_user;

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
-- Data: app_role
-- -----------------------------------------------------
-- Simple setup: user and admin
insert into app_role (`name`) values
	('USER'),
    ('ADMIN');

-- -----------------------------------------------------
-- Data: app_user
-- -----------------------------------------------------
-- Initial data to get started, passwords are set to "P@ssw0rd!" for now
insert into app_user (username, password_hash, email) values
	('johnjsmith', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'john.smith@gmail.com'),
    ('sallyjones1989', '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', 'sallymjones1989@hotmail.com');

-- -----------------------------------------------------
-- Data: app_user_role
-- -----------------------------------------------------
-- Initial app users with roles; account 1 is an Admin, account 2 is a User.
insert into app_user_role (app_user_id, app_role_id) values 
	(1, 2),
    (2, 1);

-- -----------------------------------------------------
-- Data: avatar
-- -----------------------------------------------------
insert into avatar (avatar_img_url, avatar_description) values
	('https://static.thenounproject.com/png/3826021-200.png', 'Winter Bird Watcher'),
    ('https://static.thenounproject.com/png/77049-200.png', 'Bird Watching Buddies'),
    ('https://static.thenounproject.com/png/203210-200.png', 'Owl In a Tree'),
    ('https://static.thenounproject.com/png/411938-200.png', 'Telephoto Lens Watcher'),
    ('https://static.thenounproject.com/png/2946574-200.png', 'Bird Family'),
    ('https://static.thenounproject.com/png/2946575-200.png', 'Bird Eating'),
    ('https://static.thenounproject.com/png/3890960-200.png', 'Bird House'),
    ('https://static.thenounproject.com/png/1188264-200.png', 'Bird in Binoculars'),
    ('https://static.thenounproject.com/png/1043242-200.png', 'Trees'),
    ('https://static.thenounproject.com/png/6811-200.png', 'Crow'),
    ('https://static.thenounproject.com/png/502014-200.png', 'Binoculars'),
    ('https://static.thenounproject.com/png/464142-200.png', 'Owl'),
    ('https://static.thenounproject.com/png/4451522-200.png', 'Bird on Binoculars'),
    ('https://static.thenounproject.com/png/4273916-200.png', 'Feather'),
    ('https://static.thenounproject.com/png/203166-200.png', 'Wheelchair Watcher'),
    ('https://static.thenounproject.com/png/92148-200.png', 'Telephoto Lens Camera');
    
    
-- -----------------------------------------------------
-- Data: user_avatar
-- -----------------------------------------------------
insert into user_avatar (app_user_id, avatar_id) values
	(1, 1),
    (2, 2);
    
-- -----------------------------------------------------
-- Data: sighting
-- -----------------------------------------------------
insert into sighting (app_user_id, bird_id, sighting_date, city, state, daytime) values
	(1, 1, '2020-10-01', 'Harlingen', 'Texas', true),
	(1, 2, '2021-11-01', 'New York', 'New York', true),
	(1, 3, '2021-12-01', 'Toledo', 'Ohio', false),
	(2, 4, '2022-02-01', 'Point Reyes N.S', 'California', false),
	(2, 5, '2022-02-07', 'Tucson', 'Arizona', true),
    (2, 6, '2022-03-16', 'Cape May', 'New Jersey', false),
    (2, 7, '2022-03-07', 'Cave Creek Canyon', 'Arizona', true),
    (1, 8, '2020-10-02', 'Dauphin Island', 'Alabama', true),
    (2, 9, '2021-12-15', 'Grand Isle', 'Louisiana', true),
    (1, 10, '2022-06-01', 'Nome', 'Alaska', false),
    (1, 18, '2022-06-06', 'Santa Maria Valley', 'California', false),
    (1, 16, '2020-04-05', 'Rio Grande Valley', 'Texas', true),
    (2, 13, '2020-06-10', 'Wisconsin Point', 'Wisconsin', true),
    (2, 2, '2020-05-12', 'Plum Island', 'Massachusetts', true);
    
-- -----------------------------------------------------
-- Data: sighting_trait
-- -----------------------------------------------------
insert into sighting_trait (sighting_id, trait_id) values
	(1, 1),
    (1, 6),
    (2, 2),
    (3, 7),
    (3, 8),
    (4, 3),
    (4, 4),
    (5, 6),
    (5, 8),
    (6, 1),
    (6, 6),
    (6, 9),
    (7, 5),
    (8, 2),
    (9, 8),
    (9, 7),
    (10, 4),
    (10, 8),
    (11, 1),
    (12, 9),
    (13, 6),
    (13, 9),
    (14, 1),
    (14, 6),
    (14, 9),
    (14, 7);