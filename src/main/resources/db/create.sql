drop table if exists addresses;
drop table if exists restaurants;
drop table if exists products;
drop table if exists orders;
drop table if exists orderproducts;
drop table if exists clients;

CREATE TABLE addresses (
   id int PRIMARY KEY AUTO_INCREMENT,
   street varchar(255) NOT NULL,
   neighborhood varchar(255) NOT NULL,
   city varchar(255) NOT NULL,
   zipCode int NOT NULL,
   additionalInformation varchar(255) NULL
);

CREATE TABLE clients (
    id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    document varchar(20) NOT NULL
);

CREATE TABLE restaurants (
    id int PRIMARY KEY auto_increment,
    name varchar(255) NOT NULL,
    addressId int NOT NULL,
    phone varchar(20) NOT NULL,
    FOREIGN KEY (addressId) REFERENCES addresses (id)
);

CREATE TABLE products (
    id int PRIMARY KEY AUTO_INCREMENT,
    productName VARCHAR,
    productDescription VARCHAR,
    productPrice decimal,
    restaurantId INTEGER,
    FOREIGN KEY (restaurantId) REFERENCES restaurants (id)
);

CREATE TABLE orders (
    id int PRIMARY KEY AUTO_INCREMENT,
    clientId INTEGER,
    addressId INTEGER,
    orderStatus INTEGER,
    orderDate DATE,
    FOREIGN KEY (clientId) REFERENCES clients (id),
    FOREIGN KEY (addressId) REFERENCES addresses (id)
);

CREATE TABLE orderproducts (
    id int PRIMARY KEY AUTO_INCREMENT,
    orderId int NOT NULL,
    productId int NOT NULL,
    quantity int NOT NULL,
    FOREIGN KEY (orderId) REFERENCES orders (id),
    FOREIGN KEY (productId) REFERENCES products (id)
);