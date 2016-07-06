create table customers(customer_id int primary key auto_increment, name varchar(30) not null, contact varchar(30) not null, address varchar(100) not null);

create table orders(order_id int primary key auto_increment, order_date date not null, amount numeric(10,2) not null, customer_id int not null, foreign key(customer_id) references customers(customer_id));