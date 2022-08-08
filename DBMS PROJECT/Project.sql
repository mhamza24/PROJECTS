create database project;
use project;
CREATE TABLE Car (
    Car_ID int primary key auto_increment not null,
    Serial_No int not null,
    Make VARCHAR(25) not null,
    Model VARCHAR(25) not null,
    Color VARCHAR(25) not null,
    Year varchar(25) not null,
    price float not null,
    Sale_Status varchar(25) not null
);
CREATE TABLE Customer (
    Customer_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    First_Name VARCHAR(25) not null,
    Last_Name VARCHAR(25) not null,
    Phone_Number VARCHAR(25) not null,
    Address VARCHAR(25) not null,
    City VARCHAR(25) not null,
    Province VARCHAR(25) not null,
    Country VARCHAR(25) not null,
    Postal_code VARCHAR(25) not null
);
CREATE TABLE Salesperson (
    Salesperson_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    First_Name VARCHAR(25) NOT NULL,
    Last_Name VARCHAR(25) NOT NULL
);
CREATE TABLE Sales_Invoice (
    Sales_Invoice_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Date varchar(25)  NOT NULL,
    Car_ID int  NOT NULL,
    Customer_ID int  NOT NULL,
    Salesperson_ID int  NOT NULL,
    price float not null,
    FOREIGN KEY (Car_ID) REFERENCES Car(Car_ID),
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID),
    FOREIGN KEY (Salesperson_ID) REFERENCES Salesperson(Salesperson_ID)
);
create table Service_ticket(
	Service_ticket_ID int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Service_ticket_No int not null,
	Car_ID int not null, 
    Customer_ID int not null,
    Received_Date date not null,
    Return_Date date not null,
    Comments varchar(25),
    Mechanic_id int not null,
    price float  not null,
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID),
    FOREIGN KEY (Car_ID) REFERENCES Car(Car_ID),
    FOREIGN KEY (Mechanic_id) REFERENCES Mechanic(Mechanic_ID)
    
);

CREATE TABLE Mechanic (
    Mechanic_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    First_Name VARCHAR(25) NOT NULL,
    Last_Name VARCHAR(25) NOT NULL
);
select CONCAT(First_Name,Last_Name),Mechanic_ID from Mechanic;
create table Parts(
	Part_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Part_no int not null,
    Desciption varchar(25),
    Price float,
    units int not null
);
insert into Salesperson values
(1,"AAYAN","Khan"),
(2,"Muhammad","IMRAN"),
(3,"MOHSIN","Ahmed"),
(4,"SOHAIL","Tanveer");


insert into Mechanic values
(1,"Aslam","Khan"),
(2,"Muhammad","Sohaib"),
(3,"Shakoor","Sheikh"),
(4,"Muhammad","Haroon");

insert into Parts value
(1,1,"Car Radiator",1500,18),
(2,2,"Engine Cylinder Cover",1000,4),
(3,3,"Oil Filter Componentsr",800,35),
(4,4,"Oil Filter",1200,35),
(5,5,"Brake Disc",3500,74),
(6,6,"Bumper",9500,12),
(7,7," Engine Fan",2500,25),
(8,8," Windscreen Wipers",850,87),
(9,9,"Engine Cylinder Cover",10000,13),
(10,10,"Radiator Support",7000,6),
(11,11,"Header and Nose Panel",11000,12),
(12,12,"Headlights",35000,20),
(13,13,"Backlights",30000,18),
(14,14,"Wheels",60000,126),
(15,15,"Axle",23000,62),
(16,16,"Piston",14000,87),
(17,17,"Transmission Gears",8000,39),
(18,18,"Side Mirros",14000,73),
(19,19," Speedometer",15000,19),
(20,20,"Wind Screen",25000,24);






show tables;

select Max(Customer_ID) from customer;

select * from car;
select * from customer;
select * from mechanic;
select * from parts;
select * from sales_invoice;
select * from salesperson;
select * from service_ticket;



describe Salesperson;
describe Service_ticket;
select * from Service_ticket;
	
select * from Car where Make like 'Honda';	
describe Parts; 
select * from Parts;
drop table Parts;

select * from Parts where Part_ID=3;
select * from Parts where Part_ID=17;






	

