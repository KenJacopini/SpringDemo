drop user if exists 'kenneth'@'localhost';

create user 'kenneth'@'localhost' identified by 'spring_Demo1';

drop database if exists springDemoDB;

create database springDemoDB;

use springDemoDB;

grant all privileges on springDemoDB.* to 'kenneth'@'localhost';

flush privileges;