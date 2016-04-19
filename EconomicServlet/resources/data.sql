CREATE TABLE clients(
id_client INT NOT NULL,
name VARCHAR(30),
lastname VARCHAR(30),
surname VARCHAR(30),
city VARCHAR(30),
country VARCHAR(30),
post_index INT,
password VARCHAR(30),
isadmin BOOLEAN,
PRIMARY KEY (id_client)
);
 
CREATE TABLE cards (
id_card INT NOT NULL,
id_client INT,
NUMBER INT,
year_of_end INT,
PRIMARY KEY (id_card),
FOREIGN KEY (id_client) REFERENCES clients (id_client) ON DELETE CASCADE ON UPDATE CASCADE
);
 
CREATE TABLE providers(
id_provider INT NOT NULL,
address VARCHAR(70),
title VARCHAR(30),
description VARCHAR(70),
PRIMARY KEY (id_provider)
);
 
CREATE TABLE goods (
id_good INT NOT NULL,
id_provider INT,
name VARCHAR(30),
price DECIMAL(10),
description VARCHAR(70),
category VARCHAR(40),
count_on_stock INT,
image_path VARCHAR(500),
PRIMARY KEY (id_good),
FOREIGN KEY (id_provider) REFERENCES providers (id_provider) ON DELETE CASCADE ON UPDATE CASCADE
);
 
CREATE TABLE orders(
id_order INT NOT NULL,
id_client INT,
client VARCHAR(50),
DATE DATE,
price DECIMAL(30),
STATUS BOOLEAN,
PRIMARY KEY (id_order),
FOREIGN KEY (id_client ) REFERENCES clients (id_client ) ON DELETE CASCADE ON UPDATE CASCADE
);
 
CREATE TABLE order_good(
id_order INT,
id_good INT,
FOREIGN KEY (id_order) REFERENCES orders (id_order) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (id_good) REFERENCES goods (id_good) ON DELETE CASCADE ON UPDATE CASCADE
);
 
CREATE TABLE regular_orders(
id_regord INT NOT NULL,
id_client INT,
count_of_goods INT,
name VARCHAR(50),
price DECIMAL(10),
count_of_months INT,
PRIMARY KEY (id_regord),
FOREIGN KEY (id_client) REFERENCES clients (id_client) ON DELETE CASCADE ON UPDATE CASCADE
);

insert into clients (id_client, name, lastname, surname, city, country, post_index, password, isadmin) values (1,'Дмитрий', 'Александрович', 'Селуков', 'Пермь', 'Россия', 614000, '123', false);
insert into clients (id_client, name, lastname, surname, city, country, post_index, password, isadmin) values (2,'Александр', 'Иванович', 'Бородач', 'Москва', 'Россия', 246091, '908712',false);
insert into clients (id_client, name, lastname, surname, city, country, post_index, password, isadmin) values (3, 'Михаил', 'Сергеевич', 'Тимченко', 'Брянск', 'Россия', 241018, '785641',false);
insert into clients (id_client, name, lastname, surname, city, country, post_index, password, isadmin) values (4, 'admin', 'admin', 'admin', 'adminsk', 'adminia', 127001, 'admin',true);
insert into clients (id_client, name, lastname, surname, city, country, post_index, password, isadmin) values (5, 'user', 'user', 'user', 'usersk', 'useria', 69, 'user',false);

insert into cards (id_card, id_client, number, year_of_end) values (1, 1, 1234567898, 2016);
insert into cards (id_card, id_client, number, year_of_end) values (2, 2, 985467185, 2020);
insert into cards (id_card, id_client, number, year_of_end) values (3, 3, 674198531, 2018);

insert into providers (id_provider, address, title, description) values (1, 'Los-Angeles', 'US Jeans', 'Качественные джинсы ручной работы');
insert into providers (id_provider, address, title, description) values (2, 'Ancara', 'TurkeyJ', 'Турецкий джинсы, привезенные из столицы');
insert into providers (id_provider, address, title, description) values (3, 'China', 'GJ', 'Джинсы фабричного производства');
insert into providers (id_provider, address, title, description) values (4, 'Tailand', 'Law', 'Футболки фабричные');

insert into goods (id_good, id_provider, name, price, description, category, count_on_stock, image_path) values (1, 1, 'Синие джинсы', 4000, 'Американские джинсы высокого качества', 'Джинсы', 20, 'resources/img/jeans1.jpg');
insert into goods (id_good, id_provider, name, price, description, category, count_on_stock, image_path) values (2, 2, 'джинсыыыы', 3000, 'Обычные солдатские джинсы', 'Джинсы', 15, 'resources/img/jeans2.jpg');
insert into goods (id_good, id_provider, name, price, description, category, count_on_stock, image_path) values (3, 3, 'Чёрные джинсы', 5000, 'Хит сезона', 'Джинсы', 30, 'resources/img/jeans3.jpg');
insert into goods (id_good, id_provider, name, price, description, category, count_on_stock, image_path) values (4, 4, 'Желтая футболка', 2000, 'Летние легкие футболки', 'Футболки', 27, 'resources/img/t-shirt-1.png');

insert into orders (id_order, id_client, date, price, status) values (1, 1, '2014-10-12', 8000, true);
insert into orders (id_order, id_client, date, price, status) values (2, 2, '2016-09-03', 20000, true);

insert into regular_orders (id_regord, id_client, count_of_goods, name, price, count_of_months) values (1, 1, 3, 'Синие джинсы', 4000, 4);
insert into regular_orders (id_regord, id_client, count_of_goods, name, price, count_of_months) values (2, 2, 2, 'Желтая футболка', 2000, 2);

insert into order_good (id_order, id_good) values (1, 1);
insert into order_good (id_order, id_good) values (2, 2);
insert into order_good (id_order, id_good) values (2, 3);