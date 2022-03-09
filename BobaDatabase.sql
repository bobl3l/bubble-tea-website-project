create database if not exists bigboba;
 
use bigboba;
 
drop table if exists cheezho;
create table cheezho (
  id     varchar(50),
  name  varchar(50),
  price  float,
  primary key (id));
 
insert into cheezho values ('A1','CheezHO Black Tea',10.00);
insert into cheezho values ('A2','CheezHO Green Tea',10.00);
insert into cheezho values ('A3','CheezHO Oolong Tea',10.00);
insert into cheezho values ('A4','CheezHO Melon Tea',10.00);
insert into cheezho values ('A5','CheezHO Peach Jing Syuan Slush',14.90);
insert into cheezho values ('A6','CheezHO Mango Slush',14.90);

drop table if exists brew;
create table brew (
  id     varchar(50),
  name  varchar(50),
  price  float,
  primary key (id));

insert into brew values ('B1','Traditional Black Tea',6.00);
insert into brew values ('B2','Jasmine Green Tea',6.00);
insert into brew values ('B3','Roasted Oolong Tea',6.00);
insert into brew values ('B4','Winter Melon Tea',6.00);
insert into brew values ('B5','Honey Green Tea',8.00);
insert into brew values ('B6','Green Tea Yakult',8.00);

drop table if exists milk;
create table milk (
  id     varchar(50),
  name  varchar(50),
  price  float,
  primary key (id));

insert into milk values ('C1','Classic Milk Tea',7.00);
insert into milk values ('C2','Earl Grey Milk Tea',7.00);
insert into milk values ('C3','Winter Melon Milk Tea',7.00);
insert into milk values ('C4','Brown Sugar Milk Tea',8.00);
insert into milk values ('C5','Chocolate Milk',8.00);
insert into milk values ('C6','Salted Caramel Milk Tea',9.50);
insert into milk values ('C7','Golden Avocado Milk',15.90);

drop table if exists fruit;
create table fruit (
  id     varchar(50),
  name  varchar(50),
  price  float,
  primary key (id));

insert into fruit values ('D1','Lychee Rose Jing Syuan Tea',8.90);
insert into fruit values ('D2','Fresh Lemon Black Tea',9.00);
insert into fruit values ('D3','Honey Fresh Lemon Juice',9.00);
insert into fruit values ('D4','Sakura Ai Yu Lychee Rose',10.90);
insert into fruit values ('D5','Triple Peach Yakult',12.90);
insert into fruit values ('D6','Mango Delight',13.90);
insert into fruit values ('D7','Singapore Fruit Tea',14.90);


drop table if exists tea;
create table tea (
  id     varchar(50),
  name  varchar(50),
  price  float,
  qty int,
  primary key (id));

insert into tea values ('A1','CheezHO Black Tea',10.00,100);
insert into tea values ('A2','CheezHO Green Tea',10.00,100);
insert into tea values ('A3','CheezHO Oolong Tea',10.00,100);
insert into tea values ('A4','CheezHO Melon Tea',10.00,100);
insert into tea values ('A5','CheezHO Peach Jing Syuan Slush',14.90,100);
insert into tea values ('A6','CheezHO Mango Slush',14.90,100);
insert into tea values ('B1','Traditional Black Tea',6.00,100);
insert into tea values ('B2','Jasmine Green Tea',6.00,100);
insert into tea values ('B3','Roasted Oolong Tea',6.00,100);
insert into tea values ('B4','Winter Melon Tea',6.00,100);
insert into tea values ('B5','Honey Green Tea',8.00,100);
insert into tea values ('B6','Green Tea Yakult',8.00,100);
insert into tea values ('C1','Classic Milk Tea',7.00,100);
insert into tea values ('C2','Earl Grey Milk Tea',7.00,100);
insert into tea values ('C3','Winter Melon Milk Tea',7.00,100);
insert into tea values ('C4','Brown Sugar Milk Tea',8.00,100);
insert into tea values ('C5','Chocolate Milk',8.00,100);
insert into tea values ('C6','Salted Caramel Milk Tea',9.50,100);
insert into tea values ('C7','Golden Avocado Milk',15.90,100);
insert into tea values ('D1','Lychee Rose Jing Syuan Tea',8.90,100);
insert into tea values ('D2','Fresh Lemon Black Tea',9.00,100);
insert into tea values ('D3','Honey Fresh Lemon Juice',9.00,100);
insert into tea values ('D4','Sakura Ai Yu Lychee Rose',10.90,100);
insert into tea values ('D5','Triple Peach Yakult',12.90,100);
insert into tea values ('D6','Mango Delight',13.90,100);
insert into tea values ('D7','Singapore Fruit Tea',14.90,100);



select * from cheezho;
select * from brew;
select * from milk;
select * from fruit;
select * from tea;
