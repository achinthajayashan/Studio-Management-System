DROP DATABASE IF EXISTS studio_matheesha;
CREATE DATABASE studio_matheesha;
USE studio_matheesha;

CREATE TABLE IF NOT EXISTS employee(
	employee_ID VARCHAR(3) PRIMARY KEY,
	emp_name VARCHAR(30) NOT NULL,
	emp_NIC VARCHAR(12) NOT NULL,
	emp_dob DATE,
	emp_contact INTEGER NOT NULL,
	emp_email VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS user(
	user_name VARCHAR(15) UNIQUE,
	password VARCHAR(15) NOT NULL,
	employee_ID VARCHAR(3),
	CONSTRAINT PRIMARY KEY(user_name),
	CONSTRAINT FOREIGN KEY(employee_ID) REFERENCES employee(employee_ID)
		ON DELETE CASCADE  ON  UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS salary(
	salary_ID VARCHAR(05),
	month VARCHAR(10),
	status BOOLEAN,
	employee_ID VARCHAR(3),
	CONSTRAINT PRIMARY KEY(salary_ID),
	CONSTRAINT FOREIGN KEY(employee_ID) REFERENCES employee(employee_ID)
		ON DELETE CASCADE  ON  UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS team(
	team_ID VARCHAR(05) UNIQUE,
	team_name VARCHAR(10),
	employee_ID VARCHAR(3),
	CONSTRAINT PRIMARY KEY(team_ID),
	CONSTRAINT FOREIGN KEY(employee_ID) REFERENCES employee(employee_ID)
		ON DELETE CASCADE  ON  UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS instrument(
	instrument_ID VARCHAR(05),
	instrument_name VARCHAR(30),
	team_ID VARCHAR(05),
	CONSTRAINT PRIMARY KEY(instrument_ID),
	CONSTRAINT FOREIGN KEY(team_ID) REFERENCES team(team_ID)
		ON DELETE CASCADE  ON  UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS package(
	package_ID VARCHAR(05),
	package_name VARCHAR(30),
	price DOUBLE,
	CONSTRAINT PRIMARY KEY(package_ID)
);

CREATE TABLE IF NOT EXISTS customer(
	customer_ID VARCHAR(05),
	customer_name VARCHAR(30),
	address VARCHAR(100),
	contact INTEGER,
	cus_email TEXT,
	CONSTRAINT PRIMARY KEY(customer_ID)
);

CREATE TABLE IF NOT EXISTS book(
	booking_ID VARCHAR(08),
	location VARCHAR(30),
	date DATE,
	customer_ID VARCHAR(05),
	package_ID VARCHAR(05),
	CONSTRAINT PRIMARY KEY(booking_ID),
	CONSTRAINT FOREIGN KEY(customer_ID) REFERENCES customer(customer_ID)
		ON DELETE CASCADE  ON  UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(package_ID) REFERENCES package(package_ID)
		ON DELETE CASCADE  ON  UPDATE CASCADE
);

drop table orders;
CREATE TABLE IF NOT EXISTS orders(
	order_ID VARCHAR(10),
	date DATE,
	customer_ID VARCHAR(05),
	CONSTRAINT PRIMARY KEY(order_ID),
	CONSTRAINT FOREIGN KEY(customer_ID) REFERENCES customer(customer_ID)
		ON DELETE CASCADE  ON  UPDATE CASCADE
);

INSERT INTO orders VALUES('O001',null,'C001');

drop table payment;
CREATE TABLE IF NOT EXISTS payment(
	payment_ID VARCHAR(08),
	value DOUBLE,
	date DATE,
	payment_type ENUM ('Half payment' , 'Full payment') DEFAULT 'Full payment',
	order_ID VARCHAR(10),
	booking_ID VARCHAR(08),
	CONSTRAINT PRIMARY KEY(payment_ID),
	CONSTRAINT FOREIGN KEY(order_ID) REFERENCES orders(order_ID)
		ON DELETE CASCADE  ON  UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(booking_ID) REFERENCES book(booking_ID)
		ON DELETE CASCADE  ON  UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS item(
	item_ID VARCHAR(05),
	item_name VARCHAR(30),
	unit_price DOUBLE NOT NULL,
	qty_on_hnd INTEGER,
	supplier_ID VARCHAR(05),
	CONSTRAINT PRIMARY KEY(item_ID),
	CONSTRAINT FOREIGN KEY(supplier_ID) REFERENCES supplier(supplier_ID)
		ON DELETE CASCADE  ON  UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS order_detail(
	item_ID VARCHAR(05),
	order_ID VARCHAR(10),
	qty INTEGER,
	unitPrice DECIMAL(8,2),
	CONSTRAINT PRIMARY KEY(item_ID,order_ID),
	CONSTRAINT FOREIGN KEY(order_ID) REFERENCES orders(order_ID)
		ON DELETE CASCADE  ON  UPDATE CASCADE,
	CONSTRAINT FOREIGN KEY(item_ID) REFERENCES item(item_ID)
		ON DELETE CASCADE  ON  UPDATE CASCADE
);


CREATE TABLE IF NOT EXISTS supplier(
	supplier_ID VARCHAR(05),
	supplier_name VARCHAR(30),
	address VARCHAR(30),
	contact INTEGER(10),
	CONSTRAINT PRIMARY KEY(supplier_ID)
);


