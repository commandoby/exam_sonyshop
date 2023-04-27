-- add images
SET @Uploads := "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/";
SET @UploadsPhone := "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/phone/";

DELETE FROM sonyshop.images WHERE id >= 0;
INSERT INTO sonyshop.images(id, image)
VALUES (1, LOAD_FILE(CONCAT(@Uploads,"phone.jpeg"))),
(2, LOAD_FILE(CONCAT(@Uploads,"player.jpeg"))),
(3, LOAD_FILE(CONCAT(@Uploads,"headphones.jpeg"))),
(4, LOAD_FILE(CONCAT(@Uploads,"tv.jpeg"))),
(5, LOAD_FILE(CONCAT(@Uploads,"photo.jpeg"))),
(6, LOAD_FILE(CONCAT(@Uploads,"memcard.jpeg"))),
(7, LOAD_FILE(CONCAT(@UploadsPhone,"10_II_XQ-AU52_Dual.jpeg")));

-- add users
DELETE FROM sonyshop.users WHERE id >= 0;
INSERT INTO sonyshop.users(name, surname, email, password, date_of_birth, balance)
VALUES ("Admin", "Admin", "admin", "admin", "1980-01-01", 9999999);

-- add categories
DELETE FROM sonyshop.categories WHERE id >= 0;
INSERT INTO sonyshop.categories(id, name, tag, rating, image_id)
VALUES (1, "Phone", "phone", 1, 1),
       (2, "MP3 player", "player", 1, 2),
       (3, "Headphones", "headphones", 1, 3),
       (4, "TV", "tv", 1, 4),
       (5, "Photo", "photo", 1, 5),
       (6, "Memory card", "memcard", 1, 6);

-- add products
DELETE FROM sonyshop.products WHERE id >= 0;
INSERT INTO sonyshop.products(name, description, price, quantity, category_id, image_id)
VALUES ("Sony Xperia 10 II XQ-AU52 Dual",
        "Android, экран 6\" OLED (1080x2520), Qualcomm Snapdragon 665, ОЗУ 4 ГБ, флэш-память 128 ГБ,
карты памяти, камера 12 Мп, аккумулятор 3600 мАч, 2 SIM", 899, 24, 1, 7);