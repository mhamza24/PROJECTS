create database summerProject;
use summerProject;

CREATE TABLE Customer (
    Customer_ID INT PRIMARY KEY AUTO_INCREMENT,
    First_Name VARCHAR(25) not null,
    Last_Name VARCHAR(25) not null,
    Phone_Number VARCHAR(25) not null,
    email VARCHAR(50) not null,
    Username VARCHAR(50) not null,
    password varchar(25)not null,
    Address VARCHAR(25) not null,
    City VARCHAR(25) not null,
    Country VARCHAR(25) not null,
    Postal_code VARCHAR(25) not null
    
);

CREATE TABLE Customeroption (
    CustomerOptionID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	2factorauthentication bool,
    accountStatus bool not null,
    report int not null,
    FOREIGN KEY (CustomerOptionID) REFERENCES Customer(Customer_ID)
);
CREATE TABLE Car (
    Car_ID int primary key auto_increment not null,
    customer_id int not null,
    Make VARCHAR(25) not null,
    Model VARCHAR(25) not null,
    varient varchar(30) not null,
    enginecapacity varchar(25) not null,
    Color VARCHAR(25) not null,
    Year varchar(25) not null,
    price float not null,
    Sale_Status bool,
    additionalInformation varchar(150) not null,
    fastersale bool,
    views int ,
    datetime varchar(75),
    FOREIGN KEY (customer_id) REFERENCES Customer(Customer_ID)
    
);
CREATE TABLE reportedAccounts 
(
    reportedAccountsID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	reporterID int unique,
    reportedID int ,
    comments varchar(150),
    FOREIGN KEY (reporterID) REFERENCES Customer(Customer_ID),
    FOREIGN KEY (reportedID) REFERENCES Customer(Customer_ID)
);

CREATE TABLE Sales_Invoice (
    Sales_Invoice_ID INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    Date varchar(25)  NOT NULL,
    Car_ID int  NOT NULL unique,
    Customer_ID int  NOT NULL,
    price float not null,
    FOREIGN KEY (Car_ID) REFERENCES Car(Car_ID),
    FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID)
);

show tables;