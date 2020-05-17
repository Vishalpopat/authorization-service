--drop table if exists user CASCADE;
--
--create table user (
--user_id integer not null,
--user_name varchar(12)
--password varchar(50),
--primary key (id)
--);

insert into user (user_id, user_name, password) values 
	(101, 'Sharad', '$2a$04$J6oPlv2.Q4z6jUMKK0RwmeUkBlTppe4KCiqIlvxV9zcQjCY7wrYQ.'),
	(102, 'Vishal', '$2a$04$zYY2KKnx7yU8XRO41.54YuGOVSN88.Slz3NcwF2CXpTIo1yTSlAk6'),
	(103, 'Mitul', '$2a$04$iOR95iiGtblM4N8v7I.D..p0PQWT0moqEviTbyypcvPhUz5OCB5ka');

