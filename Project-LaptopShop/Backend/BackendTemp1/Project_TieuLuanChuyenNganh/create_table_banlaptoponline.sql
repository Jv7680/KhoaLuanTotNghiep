DROP DATABASE laptopshoponline;

CREATE DATABASE laptopshoponline;

USE laptopshoponline;
CREATE TABLE accounts(
	account_id int primary key auto_increment,
	username varchar(50) not null unique,
	passwords varchar(200),
	firstname varchar(50) charset utf8mb4 not null,
	lastname varchar(50) charset utf8mb4 not null,
    address varchar(150),
	gmail varchar(50) not null,
	phonenumber varchar(50),
	activation_code varchar(50),
	passwordreset_code varchar(50),
	active_account int,
	provider varchar(50),
	roles VARCHAR(10)
);

CREATE TABLE news
(
    news_id int primary key auto_increment,
    account_id int not null,
    title varchar(200) charset utf8mb4,
    content varchar(4000) charset utf8mb4,
    image_link varchar(50),
    created date,
    isdeleted int,
    CONSTRAINT PRO_ADMIN_ID_FK FOREIGN KEY (account_id) REFERENCES accounts(account_id) ON DELETE CASCADE
);

CREATE TABLE supplier
(
    supplier_id int primary key auto_increment,
    supplier_name varchar(50) charset utf8mb4 not null,
    image_link VARCHAR(500),
    isdeleted int
);

CREATE TABLE product
(
    product_id int primary key auto_increment,
    product_name  nvarchar(50) not null,
    supplier_id int not null,
    quantity int not null,
    image varchar(500),
    unitprice INT not null,
    discount varchar(10),
    description varchar(500) charset utf8mb4,
    isdeleted int,
    CONSTRAINT PRO_SUPPLIER_ID_FK FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id) ON DELETE CASCADE
);

CREATE TABLE cart
(
	cart_id int primary key auto_increment,
    account_id int,
    product_id int,
    cart_product_quantity INT,
    isdeleted int,
    CONSTRAINT CART_PRODUCT_ID_FK FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE,
    CONSTRAINT CART_CUSTOMER_ID_FK FOREIGN KEY(account_id) REFERENCES accounts(account_id) ON DELETE CASCADE
);

CREATE TABLE orders
(
    order_id int primary key auto_increment,
    account_id int not null,
    order_date date not null,
    receipt_date date,
    total_amount int,
    shipping int,
    address varchar(50) charset utf8mb4 not null,
    customer_note varchar(50) charset utf8mb4,
    status int,
    CONSTRAINT ORDER_ACCOUNT_ID_FK FOREIGN KEY(account_id) REFERENCES accounts(account_id) ON DELETE CASCADE
);

CREATE TABLE orderdetail(
	order_id int not null,
	product_id int not null,
	quantity int not null,
	CONSTRAINT ORDERDETAIL_PK PRIMARY KEY(order_id, product_id),
    CONSTRAINT ORDERDETAIL_ORDER_ID_FK FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    CONSTRAINT ORDERDETAIL_PRODUCT_ID_FK FOREIGN KEY(product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

CREATE TABLE reviews(
	order_id int,
	product_id int,
	account_id int,
	contents varchar(500) charset utf8mb4,
	rate varchar(10),
    reviews_date date,
    isdeleted int,
	CONSTRAINT REVIEWS_PK PRIMARY KEY(order_id, product_id, account_id),
	CONSTRAINT REVIEWS_ORDER_ID_FK FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
	CONSTRAINT REVIEWS_PRODUCT_ID_FK FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE,
	CONSTRAINT REVIEWS_ACCOUNT_ID_FK FOREIGN KEY (account_id) REFERENCES accounts(account_id) ON DELETE CASCADE
);

