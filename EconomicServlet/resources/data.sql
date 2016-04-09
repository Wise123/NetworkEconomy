Create table admins (
id_admin int NOT NULL,
login varchar(255),
password int,
email varchar(255),
PRIMARY KEY (id_admin),
);

Create table clients( 
id_client int NOT NULL, 
id_admin int,
name varchar(30), 
lastname varchar(30), 
surname varchar(30), 
city varchar(30), 
country varchar(30), 
post_index int, 
password int, 
PRIMARY KEY (id_client),
FOREIGN KEY (id_admin) REFERENCES admins (id_admin)
);

Create table cards (
id_card int NOT NULL,
id_client int,
number int,
year_of_end date,
PRIMARY KEY (id_card),
FOREIGN KEY (id_client) REFERENCES clients (id_client)
);

Create table providers(
id_provider int NOT NULL,
address varchar(70),
title varchar(30),
description varchar(70),
PRIMARY KEY (id_provider)
);

Create table goods (
id_good int NOT NULL,
id_provider int,
name varchar(30),
price decimal(10),
description varchar(70),
category varchar(40),
count_on_stock int,
image_path varchar(500),
PRIMARY KEY (id_good),
FOREIGN KEY (id_provider) REFERENCES providers (id_provider)
);

Create table orders(
id_order int NOT NULL,
id_client int,
date date,
price decimal(30),
status boolean,
PRIMARY KEY (id_order),
FOREIGN KEY (id_client ) REFERENCES clients (id_client )
);

Create table order_good( 
id_og int NOT NULL, 
id_order int, 
id_good int, 
PRIMARY KEY (id_og), 
FOREIGN KEY (id_order) REFERENCES orders (id_order), 
FOREIGN KEY (id_good) REFERENCES goods (id_good) 
);

Create table regular_orders( 
id_regord int NOT NULL, 
id_client int, 
count_of_goods int,
name varchar(50),
price decimal(10),
count_of_months int,
PRIMARY KEY (id_regord), 
FOREIGN KEY (id_client) REFERENCES clients (id_client) 
);

INSERT INTO admins (id_admin, login, password, email) VALUES (1, 'Skorb', 1234589, 'selukoff2010@yandex.ru');
INSERT INTO admins (id_admin, login, password, email) VALUES (2, 'Qwerty', 908712, 'kissmyass@gmail.com');

INSERT INTO clients (id_client, id_admin, name, lastname, surname, city, country, post_index, password) VALUES (1,1,'Дмитрий', 'Александрович', 'Селуков', 'Пермь', 'Россия', 614000, 1234589);
INSERT INTO clients (id_client, id_admin, name, lastname, surname, city, country, post_index, password) VALUES (2,2,'Александр', 'Иванович', 'Бородач', 'Москва', 'Россия', 246091, 908712);
INSERT INTO clients (id_client, id_admin, name, lastname, surname, city, country, post_index, password) VALUES (3, NULL , 'Михаил', 'Сергеевич', 'Тимченко', 'Брянск', 'Россия', 241018, 785641);

INSERT INTO cards (id_card, id_client, NUMBER, year_of_end) VALUES (1, 1, 1234567898, '2016-09-01');
INSERT INTO cards (id_card, id_client, NUMBER, year_of_end) VALUES (2, 2, 985467185, '2020-10-11');
INSERT INTO cards (id_card, id_client, NUMBER, year_of_end) VALUES (3, 3, 674198531, '2018-05-12');

INSERT INTO providers (id_provider, address, title, description) VALUES (1, 'Los-Angeles', 'US Jeans', 'Качественные джинсы ручной работы');
INSERT INTO providers (id_provider, address, title, description) VALUES (2, 'Ancara', 'TurkeyJ', 'Турецкий джинсы, привезенные из столицы');
INSERT INTO providers (id_provider, address, title, description) VALUES (3, 'China', 'GJ', 'Джинсы фабричного производства');
INSERT INTO providers (id_provider, address, title, description) VALUES (4, 'Tailand', 'Law', 'Футболки фабричные');

INSERT INTO goods(ID_GOOD, ID_PROVIDER, NAME, PRICE, DESCRIPTION, CATEGORY, COUNT_ON_STOCK, IMAGE_PATH) VALUES
(1, 1, 'Синие джинсы', 4000, 'Американские джинсы высокого качества', 'Джинсы', 20, 'resources/img/jeans1.jpg'),
(2, 2, 'джинсыыыы', 3000, 'Обычные солдатские джинсы', 'Джинсы', 15, 'resources/img/jeans2.jpg'),
(3, 3, 'Чёрные джинсы', 5000, 'Хит сезона', 'Джинсы', 30, 'resources/img/jeans3.jpg'),
(4, 4, 'Желтая футболка', 2000, 'Летние легкие футболки', 'Футболки', 27, 'resources/img/t-shirt-1.png'); 


INSERT INTO orders (id_order, id_client, DATE, price, STATUS) VALUES (1, 1, '2014-10-12', 8000, TRUE);
INSERT INTO orders (id_order, id_client, DATE, price, STATUS) VALUES (2, 2, '2016-09-03', 20000, TRUE);

INSERT INTO regular_orders (id_regord, id_client, count_of_goods, name, price, count_of_months) VALUES (1, 1, 3, 'Синие джинсы', 4000, 4);
INSERT INTO regular_orders (id_regord, id_client, count_of_goods, name, price, count_of_months) VALUES (2, 2, 2, 'Желтая футболка', 2000, 2);

INSERT INTO order_good (id_og, id_order, id_good) VALUES (1, 1, 1);
INSERT INTO order_good (id_og, id_order, id_good) VALUES (2, 2, 2);
