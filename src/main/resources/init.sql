
-- DDL for schema sonyshop
DROP SCHEMA IF EXISTS sonyshop;
CREATE SCHEMA IF NOT EXISTS sonyshop;
USE sonyshop;
    
    -- DDL for Table images
DROP TABLE IF EXISTS images;
CREATE TABLE IF NOT EXISTS images (
    id INT NOT NULL,
    imageURL VARCHAR(200) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX IDX_IMAGE_ID_UNIQUE (id ASC));

-- DDL for Table categories
DROP TABLE IF EXISTS categories;
CREATE TABLE IF NOT EXISTS categories (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    tag VARCHAR(45) NOT NULL,
    rating INT NOT NULL,
    image_id INT default 1,
    PRIMARY KEY (id),
    UNIQUE INDEX IDX_CATEGORY_ID_UNIQUE (id ASC),
    UNIQUE INDEX IDX_NAME_UNIQUE (name ASC),
    UNIQUE INDEX IDX_TAG_UNIQUE (tag ASC),
    CONSTRAINT FK_CATEGORIES_IMAGE_ID_IMAGES_ID
    FOREIGN KEY (image_id)
    REFERENCES images (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- DDL for Table users
DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    surname VARCHAR(45) NOT NULL,
    email VARCHAR(200) NOT NULL,
    password VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    balance INT,
    image_id INT,
    PRIMARY KEY (id),
    UNIQUE INDEX IDX_USER_ID_UNIQUE (id ASC),
    UNIQUE INDEX IDX_EMAIL_UNIQUE (email ASC),
    CONSTRAINT FK_USERS_IMAGE_ID_IMAGES_ID
    FOREIGN KEY (image_id)
    REFERENCES images (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- DDL for Table orders
DROP TABLE IF EXISTS orders;
CREATE TABLE IF NOT EXISTS orders (
    id INT NOT NULL AUTO_INCREMENT,
    price INT NOT NULL,
    date DATE NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX IDX_ID_UNIQUE (id ASC),
    CONSTRAINT FK_ORDERS_USER_ID_USERS_ID
    FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- DDL for Table products
DROP TABLE IF EXISTS products;
CREATE TABLE IF NOT EXISTS products (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(400) NULL,
    price INT NOT NULL,
    year INT,
    quantity INT NOT NULL,
    views INT NOT NULL DEFAULT 0,
    category_id INT NOT NULL,
    image_id INT,
    PRIMARY KEY (id),
    UNIQUE INDEX IDX_ID_UNIQUE (id ASC),
    CONSTRAINT FK_PRODUCTS_CATEGORY_ID_CATEGORIES_ID
    FOREIGN KEY (category_id)
    REFERENCES categories (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT FK_PRODUCTS_IMAGE_ID_IMAGES_ID
    FOREIGN KEY (image_id)
    REFERENCES images (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- DDL for Table orders_products
DROP TABLE IF EXISTS orders_products;
CREATE TABLE IF NOT EXISTS orders_products (
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    CONSTRAINT FK_ORDERS_PRODUCTS_ORDER_ID_ORDERS_ID
    FOREIGN KEY (order_id)
    REFERENCES orders (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT FK_ORDERS_PRODUCTS_PRODUCT_ID_PRODUCTS_ID
    FOREIGN KEY (product_id)
    REFERENCES products (id));