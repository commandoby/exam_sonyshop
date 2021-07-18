
-- DDL for schema sonyshop
DROP SCHEMA IF EXISTS sonyshop;
CREATE SCHEMA IF NOT EXISTS sonyshop;

-- DDL for Table categories
DROP TABLE IF EXISTS sonyshop.categories;
CREATE TABLE IF NOT EXISTS sonyshop.categories (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    tag VARCHAR(45) NOT NULL,
    image_name VARCHAR(200) NOT NULL,
    rating INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX IDX_CATEGORY_ID_UNIQUE (id ASC),
    UNIQUE INDEX IDX_NAME_UNIQUE (name ASC),
    UNIQUE INDEX IDX_TAG_UNIQUE (tag ASC));

-- DDL for Table users
DROP TABLE IF EXISTS sonyshop.users;
CREATE TABLE IF NOT EXISTS sonyshop.users (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    surname VARCHAR(45) NOT NULL,
    email VARCHAR(200) NOT NULL,
    password VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    balance INT,
    PRIMARY KEY (id),
    UNIQUE INDEX IDX_USER_ID_UNIQUE (id ASC),
    UNIQUE INDEX IDX_EMAIL_UNIQUE (email ASC));

-- DDL for Table orders
DROP TABLE IF EXISTS sonyshop.orders;
CREATE TABLE IF NOT EXISTS sonyshop.orders (
    id INT NOT NULL AUTO_INCREMENT,
    price INT NOT NULL,
    date DATE NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX IDX_ID_UNIQUE (id ASC),
    CONSTRAINT FK_ORDERS_USER_ID_USERS_ID
    FOREIGN KEY (user_id)
    REFERENCES sonyshop.users (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- DDL for Table products
DROP TABLE IF EXISTS sonyshop.products;
CREATE TABLE IF NOT EXISTS sonyshop.products (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    image_name VARCHAR(200) NOT NULL,
    description VARCHAR(400) NULL,
    price INT NOT NULL,
    quantity INT,
    category_id INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX IDX_ID_UNIQUE (id ASC),
    CONSTRAINT FK_PRODUCTS_CATEGORY_ID_CATEGORIES_ID
    FOREIGN KEY (category_id)
    REFERENCES sonyshop.categories (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- DDL for Table orders_products
DROP TABLE IF EXISTS sonyshop.orders_products;
CREATE TABLE IF NOT EXISTS sonyshop.orders_products (
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    CONSTRAINT FK_ORDERS_PRODUCTS_ORDER_ID_ORDERS_ID
    FOREIGN KEY (order_id)
    REFERENCES sonyshop.orders (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT FK_ORDERS_PRODUCTS_PRODUCT_ID_PRODUCTS_ID
    FOREIGN KEY (product_id)
    REFERENCES sonyshop.products (id));