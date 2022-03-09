use bigboba;
 
drop table if exists addons;
create table addons (
  id     int,
  name  varchar(50),
  price  float,
  primary key (id));
 
insert into addons values (1,'Pearl',1.50);
insert into addons values (2,'Aloe Vera',1.50);
insert into addons values (3,'Coconut Jelly',1.50);
insert into addons values (4,'Sakura Ai Yu Jelly',1.50);
insert into addons values (5,'Brown Sugar Pearl',2.00);
insert into addons values (6,'White Pearl',2.00);
insert into addons values (7,'CheezHO',4.00);


drop table if exists queue;
create table queue (
  name varchar(50),
  no int);
insert into queue values ('number',0);



  select * from addons;
  SELECT * from queue;