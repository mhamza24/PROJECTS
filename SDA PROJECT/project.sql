create database sdaproject;
use sdaproject;
CREATE TABLE info (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fname VARCHAR(25),
    lname VARCHAR(25),
    email VARCHAR(30),
    contactno VARCHAR(20),
    passwords VARCHAR(25)
);
CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    productname VARCHAR(100),
    price INT,
    quantity INT
);
describe info;
describe orders;
select * from info;
select * from orders;


