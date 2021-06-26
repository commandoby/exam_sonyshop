
-- DDL for schema SONYSHOP
DROP SCHEMA IF EXISTS SONYSHOP;
CREATE SCHEMA IF NOT EXISTS SONYSHOP;

-- DDL for Table CATEGORIES
DROP TABLE IF EXISTS SONYSHOP.CATEGORIES;
CREATE TABLE IF NOT EXISTS SONYSHOP.CATEGORIES (
    ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(45) NOT NULL,
    TAG VARCHAR(45) NOT NULL,
    IMAGE_NAME VARCHAR(200) NOT NULL,
    RATING INT NOT NULL,
    PRIMARY KEY (ID),
    UNIQUE INDEX IDX_CATEGORY_ID_UNIQUE (ID ASC),
    UNIQUE INDEX IDX_NAME_UNIQUE (NAME ASC),
    UNIQUE INDEX IDX_TAG_UNIQUE (TAG ASC));

-- DDL for Table USERS
DROP TABLE IF EXISTS SONYSHOP.USERS;
CREATE TABLE IF NOT EXISTS SONYSHOP.USERS (
    ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(45) NOT NULL,
    SURNAME VARCHAR(45) NOT NULL,
    EMAIL VARCHAR(200) NOT NULL,
    PASSWORD VARCHAR(50) NOT NULL,
    DATE_OF_BIRTH VARCHAR(20) NOT NULL,
    BALANCE INT NOT NULL,
    PRIMARY KEY (ID),
    UNIQUE INDEX IDX_USER_ID_UNIQUE (ID ASC),
    UNIQUE INDEX IDX_EMAIL_UNIQUE (EMAIL ASC));

-- DDL for Table ORDERS
DROP TABLE IF EXISTS SONYSHOP.ORDERS;
CREATE TABLE IF NOT EXISTS SONYSHOP.ORDERS (
    ID INT NOT NULL AUTO_INCREMENT,
    PRICE INT NOT NULL,
    DATE VARCHAR(20) NOT NULL,
    USER_ID INT NOT NULL,
    PRIMARY KEY (ID),
    UNIQUE INDEX IDX_ID_UNIQUE (ID ASC),
    CONSTRAINT FK_ORDERS_USER_ID_USERS_ID
        FOREIGN KEY (USER_ID)
        REFERENCES SONYSHOP.USERS (ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE);

-- DDL for Table PRODUCTS
DROP TABLE IF EXISTS SONYSHOP.PRODUCTS;
CREATE TABLE IF NOT EXISTS SONYSHOP.PRODUCTS (
    ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(200) NOT NULL,
    IMAGE_NAME VARCHAR(200) NOT NULL,
    DESCRIPTION VARCHAR(400) NULL,
    PRICE INT NOT NULL,
    CATEGORY_ID INT NOT NULL,
    PRIMARY KEY (ID),
    UNIQUE INDEX IDX_ID_UNIQUE (ID ASC),
    CONSTRAINT FK_PRODUCTS_CATEGORY_ID_CATEGORIES_ID
        FOREIGN KEY (CATEGORY_ID)
        REFERENCES SONYSHOP.CATEGORIES (ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE);

-- DDL for Table ORDERS_PRODUCTS
DROP TABLE IF EXISTS SONYSHOP.ORDERS_PRODUCTS;
CREATE TABLE IF NOT EXISTS SONYSHOP.ORDERS_PRODUCTS (
  ORDER_ID INT NOT NULL,
  PRODUCT_ID INT NOT NULL,
  PRIMARY KEY (ORDER_ID, PRODUCT_ID),
  CONSTRAINT FK_ORDERS_PRODUCTS_ORDER_ID_ORDERS_ID
	FOREIGN KEY (ORDER_ID)
	REFERENCES SONYSHOP.ORDERS (ID),
  CONSTRAINT FK_ORDERS_PRODUCTS_PRODUCT_ID_PRODUCTS_ID
    FOREIGN KEY (PRODUCT_ID)
	REFERENCES SONYSHOP.PRODUCTS (ID)
    ON DELETE CASCADE
    ON UPDATE CASCADE);