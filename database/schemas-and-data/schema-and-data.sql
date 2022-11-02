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
    img_url varchar(512) not null
);
  
-- -----------------------------------------------------
-- Data: trait
-- -----------------------------------------------------
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
    
-- -----------------------------------------------------
-- Data: bird
-- -----------------------------------------------------
insert into bird (common_name, scientific_name, img_url) values
	('Woodpecker', 'Picidae', 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Woodpecker_20040529_151837_1c_cropped.JPG/330px-Woodpecker_20040529_151837_1c_cropped.JPG'),
    ('Pigeon', 'Columbidae', 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Treron_vernans_male_-_Kent_Ridge_Park.jpg/330px-Treron_vernans_male_-_Kent_Ridge_Park.jpg'),
    ('Peacock', 'Pavo Cristatus', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Peacock_Plumage.jpg/330px-Peacock_Plumage.jpg'),
    ('Turkey Vulture', 'Cathartes Aura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Urubu_a_tete_rouge_-_Turkey_Vulture.jpg/330px-Urubu_a_tete_rouge_-_Turkey_Vulture.jpg'),
    ('Tree Swallow', 'Tachycineta Bicolor', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Tree_swallow_in_JBWR_%2825579%29.jpg/330px-Tree_swallow_in_JBWR_%2825579%29.jpg'),
    ('Banded Martin', 'Neophedina Cincta', 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/Banded_martin%2C_or_banded_sand_martin%2C_Riparia_cincta%2C_at_Rietvlei_Nature_Reserve%2C_Gauteng%2C_South_Africa_%2831326868871%29.jpg/330px-Banded_martin%2C_or_banded_sand_martin%2C_Riparia_cincta%2C_at_Rietvlei_Nature_Reserve%2C_Gauteng%2C_South_Africa_%2831326868871%29.jpg'),
    ('Common Gull', 'Larus Canus', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Larus_canus_Common_Gull_in_Norway.jpg/330px-Larus_canus_Common_Gull_in_Norway.jpg'),
    ('Northern Bobwhite', 'Colinus Virginianus', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Virginiawachtel_2007-06-16_065.jpg/330px-Virginiawachtel_2007-06-16_065.jpg'),
    ('Mallard Duck', 'Anas Platyrhynchos', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Anas_platyrhynchos_male_female_quadrat.jpg/330px-Anas_platyrhynchos_male_female_quadrat.jpg'),
    ('Great White Pelican', 'Pelecanus Onocrotalus', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Great_white_pelican_%28Pelecanus_onocrotalus%29.jpg/330px-Great_white_pelican_%28Pelecanus_onocrotalus%29.jpg'),
    ('Common Magpie', 'Pica Pica', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Pica_pica_-_Compans_Caffarelli_-_2012-03-16.jpg/330px-Pica_pica_-_Compans_Caffarelli_-_2012-03-16.jpg'),
    ('Cockatiel', 'Nymphicus Hollandicus', 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/cf/Cockatielmale.jpg/330px-Cockatielmale.jpg'),
    ('Wild Turkey', 'Meleagris Gallopavo', 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Gall-dindi.jpg/330px-Gall-dindi.jpg'),
    ('Whooping Crane', 'Grus Americana', 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Grus_americana_Sasata.jpg/330px-Grus_americana_Sasata.jpg'),
    ('Common Kingfisher', 'Alcedo Atthis', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/92/%E2%99%82_Common_Kingfisher_%28Alcedo_atthis%29_Photograph_By_Shantanu_Kuveskar%2C_Mangaon%2C_Maharashtra%2C_India.jpg/330px-%E2%99%82_Common_Kingfisher_%28Alcedo_atthis%29_Photograph_By_Shantanu_Kuveskar%2C_Mangaon%2C_Maharashtra%2C_India.jpg'),
    ("Allen's Hummingbird", 'Selasphorus Sasin', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Allen%27s_Hummingbird_Guarding_Flower_Patch.jpg/330px-Allen%27s_Hummingbird_Guarding_Flower_Patch.jpg'),
    ('House Sparrow', 'Passer Domesticus', 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/6e/Passer_domesticus_male_%2815%29.jpg/330px-Passer_domesticus_male_%2815%29.jpg'),
    ('Great Cormorant', 'Phalacrocorax Carbo', 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/68/Phalacrocorax_carbo_Vic.jpg/330px-Phalacrocorax_carbo_Vic.jpg');