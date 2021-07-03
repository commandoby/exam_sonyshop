-- add users
INSERT INTO sonyshop.users VALUES (NULL, "Admin", "Admin", "admin", "admin", "1980-01-01", 9999999);

-- add categories
INSERT INTO sonyshop.categories VALUES (1, "Phone", "phone", "phone.jpeg", 0);
INSERT INTO sonyshop.categories VALUES (2, "MP3 player", "player", "player.jpeg", 0);
INSERT INTO sonyshop.categories VALUES (3, "Headphones", "headphones", "headphones.jpeg", 0);
INSERT INTO sonyshop.categories VALUES (4, "TV", "tv", "tv.jpeg", 0);
INSERT INTO sonyshop.categories VALUES (5, "Photo", "photo", "photo.jpeg", 0);
INSERT INTO sonyshop.categories VALUES (6, "Memory card", "memcard", "memcard.jpeg", 0);

-- add products
INSERT INTO sonyshop.products
VALUES (NULL,
        "Sony Xperia 10 II XQ-AU52 Dual",
        "10_II_XQ-AU52_Dual.jpeg",
        "Android, экран 6\" OLED (1080x2520), Qualcomm Snapdragon 665, ОЗУ 4 ГБ, флэш-память 128 ГБ,
карты памяти, камера 12 Мп, аккумулятор 3600 мАч, 2 SIM",
        899, 24, 1);
INSERT INTO sonyshop.products
VALUES (NULL,
        "Sony Xperia 1 II XQ-AT52",
        "1_II_XQ-AT52.jpeg",
        "Android, экран 6.5\" OLED (1644x3840), Qualcomm Snapdragon 865, ОЗУ 12 ГБ, флэш-память 256 ГБ,
карты памяти, камера 12 Мп, аккумулятор 4000 мАч, 2 SIM",
        2679, 19, 1);
INSERT INTO sonyshop.products
VALUES (NULL,
        "Sony Xperia 5 II Dual",
        "5_II_Dual.jpeg",
        "Android, экран 6.1\" OLED (1080x2520), Qualcomm Snapdragon 865, ОЗУ 8 ГБ, флэш-память 128 ГБ,
карты памяти, камера 12 Мп, аккумулятор 4000 мАч, 2 SIM",
        2250, 43, 1);