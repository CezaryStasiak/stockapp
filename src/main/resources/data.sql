DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS products_on_hand;
DROP TABLE IF EXISTS shop;
DROP TABLE IF EXISTS user;


CREATE TABLE product (
  `id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `unit` varchar(45) NOT NULL,
  `price` float NOT NULL
);
INSERT INTO product (`name`, `unit`, `price`) VALUES
('CocaCola', 'liter', '4.55'),
('Sprite', 'liter', '3.75'),
('Zywiec Beer', 'liter', '5.99'),
('Bread "Swojski"', 'each', '3.40'),
('Flour', 'kilogram', '4.50'),
('Roll', 'each', '0.50'),
('Sugar "Polski Cukier"', 'kilogram', '3.50'),
('Tomatoes', 'kilogram', '2.40'),
('Lemon', 'kilogram', '4.00'),
('Cucumber', 'kilogram', '3.45'),
('Lech Beer', 'liter', '5.45'),
('Vodka "Zubrowka"', 'liter', '34.50'),
('Sausage "Podwawelska"', 'kilogram', '24.00'),
('Cigarettes L&M', 'pack', '15.00'),
('Sausage "Berlinki"', 'pack', '7.00');

CREATE TABLE products_on_hand (
  `id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `amount` float NOT NULL,
  `shop_id` int NOT NULL,
  `product_id` int NOT NULL
);

INSERT INTO products_on_hand (`amount`, `shop_id`, `product_id`) VALUES
(20, 1, 1),
(53, 1, 3),
(5, 1, 7),
(5, 1, 10),
(11.1, 1, 5),
(20, 2, 4),
(25, 2, 14),
(10, 2, 1),
( 12, 2, 10),
( 5.5, 2, 5),
( 20, 2, 11),
( 4.355, 2, 9),
( 15, 2, 8),
( 10, 2, 2),
( 20, 1, 6),
( 20, 1, 12),
( 5, 1, 4),
( 5, 1, 15),
( 5, 1, 9),
( 15, 1, 14);

CREATE TABLE shop (
  `id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL
);

INSERT INTO shop ( `name`, `address`) VALUES
( 'Zabka 1', 'Korfantego 256'),
( 'Zabka 2', 'Mickiewicza 13');


CREATE TABLE user (
  `id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `surename` varchar(45) DEFAULT NULL,
  `shop_id` int(11) NOT NULL
);

INSERT INTO user (`username`, `password`, `email`, `name`, `surename`, `shop_id`) VALUES
('czarek', 'czarek', 'sklep1@stock.pl', 'czarek', 'stasiak', 1),
('adam', 'adam', 'sklep2@stock.pl', 'adam', 'kacprzyk', 2),
('dawid', 'dawid', 'sklep1@stock.pl', 'dawid', 'andraszczyk', 1);
