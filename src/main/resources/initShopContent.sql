-- add images
SET @Uploads := "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/";
SET @UploadsPhone := "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/phone/";
SET @UploadsPlayer := "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/player/";
SET @UploadsHeadphones := "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/headphones/";
SET @UploadsTV := "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/tv/";
SET @UploadsPhoto := "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/photo/";
SET @UploadsMem := "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/memcard/";
SET @UploadsAS := "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/audiospeakers/";
SET @UploadsMicrophone := "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/microphone/";
SET @UploadsVideo := "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/video/";
SET @UploadsGame := "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/gameconsole/";

DELETE FROM sonyshop.images WHERE id >= 0;
INSERT INTO sonyshop.images(id, image)
VALUES (1, LOAD_FILE(CONCAT(@Uploads,"phone.jpeg"))),
(2, LOAD_FILE(CONCAT(@Uploads,"player.jpeg"))),
(3, LOAD_FILE(CONCAT(@Uploads,"headphones.jpeg"))),
(4, LOAD_FILE(CONCAT(@Uploads,"tv.jpeg"))),
(5, LOAD_FILE(CONCAT(@Uploads,"photo.jpeg"))),
(6, LOAD_FILE(CONCAT(@Uploads,"memcard.jpeg"))),
(7, LOAD_FILE(CONCAT(@UploadsPhone,"10_II_XQ-AU52_Dual.jpeg"))),
(8, LOAD_FILE(CONCAT(@UploadsPhone,"1_II_XQ-AT52.jpeg"))),
(9, LOAD_FILE(CONCAT(@UploadsPhone,"5_II_Dual.jpeg"))),
(10, LOAD_FILE(CONCAT(@UploadsPhone,"1.jpeg"))),
(11, LOAD_FILE(CONCAT(@UploadsPhone,"5_J9210.jpeg"))),
(12, LOAD_FILE(CONCAT(@UploadsPhone,"10_I3113_Dual.jpeg"))),
(13, LOAD_FILE(CONCAT(@UploadsPhone,"L3_I4312_Dual.jpeg"))),
(14, LOAD_FILE(CONCAT(@UploadsPhone,"10_Plus_I4293_Dual.jpeg"))),
(15, LOAD_FILE(CONCAT(@UploadsPhone,"XZ2_Dual.jpeg"))),
(16, LOAD_FILE(CONCAT(@UploadsPhone,"XA1_Plus_Dual.jpeg"))),
(17, LOAD_FILE(CONCAT(@UploadsPhone,"L1_Dual.jpeg"))),
(18, LOAD_FILE(CONCAT(@UploadsPlayer,"NW-WS623.jpeg"))),
(19, LOAD_FILE(CONCAT(@UploadsPlayer,"NW-WS413.jpg"))),
(20, LOAD_FILE(CONCAT(@UploadsPlayer,"NWZ-B183F.jpeg"))),
(21, LOAD_FILE(CONCAT(@UploadsHeadphones,"WH-1000XM4.jpeg"))),
(22, LOAD_FILE(CONCAT(@UploadsHeadphones,"WF-XB700.jpeg"))),
(23, LOAD_FILE(CONCAT(@UploadsHeadphones,"WF-1000XM3.jpeg"))),
(24, LOAD_FILE(CONCAT(@UploadsHeadphones,"WI-C200.jpeg"))),
(25, LOAD_FILE(CONCAT(@UploadsHeadphones,"PS5 Pulse 3D.jpeg"))),
(26, LOAD_FILE(CONCAT(@UploadsHeadphones,"WI-XB400.jpeg"))),
(27, LOAD_FILE(CONCAT(@UploadsHeadphones,"WH-CH510.jpeg"))),
(28, LOAD_FILE(CONCAT(@UploadsHeadphones,"WI-SP510.jpeg"))),
(29, LOAD_FILE(CONCAT(@UploadsHeadphones,"WH-CH710N.jpeg"))),
(30, LOAD_FILE(CONCAT(@UploadsHeadphones,"Gold Wireless.jpeg"))),
(31, LOAD_FILE(CONCAT(@UploadsHeadphones,"MDR-EX155AP.jpeg"))),
(32, LOAD_FILE(CONCAT(@UploadsHeadphones,"MDR-EX255AP.jpeg"))),
(33, LOAD_FILE(CONCAT(@UploadsTV,"KD-55XH9505.jpeg"))),
(34, LOAD_FILE(CONCAT(@UploadsTV,"KD-55XH9096.jpeg"))),
(35, LOAD_FILE(CONCAT(@UploadsTV,"KD-65A8.jpeg"))),
(36, LOAD_FILE(CONCAT(@UploadsTV,"KD-43XH8005.jpeg"))),
(37, LOAD_FILE(CONCAT(@UploadsTV,"KD-49XH8596.jpeg"))),
(38, LOAD_FILE(CONCAT(@UploadsTV,"KD-43XH8096.jpeg"))),
(39, LOAD_FILE(CONCAT(@UploadsTV,"KD-55XG9505.jpeg"))),
(40, LOAD_FILE(CONCAT(@UploadsTV,"KD-75ZH8.jpeg"))),
(41, LOAD_FILE(CONCAT(@UploadsTV,"KD-43XG8096.jpeg"))),
(42, LOAD_FILE(CONCAT(@UploadsTV,"KD-85ZG9.jpeg"))),
(43, LOAD_FILE(CONCAT(@UploadsTV,"KD-65AG9.jpeg"))),
(44, LOAD_FILE(CONCAT(@UploadsTV,"KD-55XF9005.jpeg"))),
(45, LOAD_FILE(CONCAT(@UploadsPhoto,"Alpha a7 III Body.jpeg"))),
(46, LOAD_FILE(CONCAT(@UploadsPhoto,"Alpha a6000 Kit.jpg"))),
(47, LOAD_FILE(CONCAT(@UploadsPhoto,"Alpha a6600 Body.jpeg"))),
(48, LOAD_FILE(CONCAT(@UploadsPhoto,"Alpha a6400 Body.jpeg"))),
(49, LOAD_FILE(CONCAT(@UploadsPhoto,"Alpha a7C Body.jpeg"))),
(50, LOAD_FILE(CONCAT(@UploadsPhoto,"Cyber-shot DSC-RX100.jpg"))),
(51, LOAD_FILE(CONCAT(@UploadsMem,"SDXC SF-E128.jpeg"))),
(52, LOAD_FILE(CONCAT(@UploadsMem,"XQD G Series.jpeg"))),
(53, LOAD_FILE(CONCAT(@UploadsMem,"SF-M Tough SDXC.jpeg"))),
(54, LOAD_FILE(CONCAT(@UploadsMem,"SDXC SF-M Series UHS-II.jpeg"))),
(55, LOAD_FILE(CONCAT(@UploadsMem,"PRO Duo MSX-M8GST.jpg"))),
(56, LOAD_FILE(CONCAT(@UploadsTV,"XR-55A90J.jpeg"))),
(57, LOAD_FILE(CONCAT(@UploadsTV,"XR-65X95J.jpeg"))),
(58, LOAD_FILE(CONCAT(@UploadsTV,"XR-55A80J.jpeg"))),
(59, LOAD_FILE(CONCAT(@UploadsTV,"KD-43XG7005.jpeg"))),
(60, LOAD_FILE(CONCAT(@UploadsTV,"KDL-43WF665.jpeg"))),
(61, LOAD_FILE(CONCAT(@UploadsTV,"KD-65XF7596.jpeg"))),
(62, LOAD_FILE(CONCAT(@UploadsTV,"KDL-32WE613.jpeg"))),
(63, LOAD_FILE(CONCAT(@UploadsTV,"KDL-40WD653.jpeg"))),
(64, LOAD_FILE(CONCAT(@UploadsPhone,"10 IV XQ-CC54.jpeg"))),
(65, LOAD_FILE(CONCAT(@UploadsPhone,"1 IV XQ-CT72.jpeg"))),
(66, LOAD_FILE(CONCAT(@UploadsPhone,"10 III XQ-BT52.jpeg"))),
(67, LOAD_FILE(CONCAT(@UploadsPhone,"5 III XQ-BQ72.jpeg"))),
(68, LOAD_FILE(CONCAT(@UploadsPhone,"1 III XQ-BC72.jpeg"))),
(69, LOAD_FILE(CONCAT(@UploadsPhone,"5 IV.jpeg"))),
(70, LOAD_FILE(CONCAT(@UploadsPhone,"10 II XQ-AU52.jpeg"))),
(71, LOAD_FILE(CONCAT(@UploadsPhone,"Ace III A203SO.jpeg"))),
(72, LOAD_FILE(CONCAT(@UploadsHeadphones,"WH-1000XM5.jpeg"))),
(73, LOAD_FILE(CONCAT(@UploadsHeadphones,"WF-1000XM4.jpeg"))),
(74, LOAD_FILE(CONCAT(@UploadsHeadphones,"WH-CH520.jpeg"))),
(75, LOAD_FILE(CONCAT(@UploadsHeadphones,"MDR7506.jpg"))),
(76, LOAD_FILE(CONCAT(@UploadsHeadphones,"WH-XB910N.jpeg"))),
(77, LOAD_FILE(CONCAT(@UploadsHeadphones,"MDR-XD150.jpeg"))),
(78, LOAD_FILE(CONCAT(@UploadsHeadphones,"LinkBuds WF-L900.jpeg"))),
(79, LOAD_FILE(CONCAT(@UploadsHeadphones,"MDR-XB55AP.jpeg"))),
(80, LOAD_FILE(CONCAT(@UploadsMem,"microSDHC (Class 10) 8GB.jpeg"))),
(81, LOAD_FILE(CONCAT(@UploadsMem,"microSDHC (Class 10) 32GB.jpg"))),
(82, LOAD_FILE(CONCAT(@UploadsMem,"microSDXC UHS-I (Class 10) 64GB.jpg"))),
(83, LOAD_FILE(CONCAT(@UploadsTV,"XR-55X90J.jpeg"))),
(84, LOAD_FILE(CONCAT(@UploadsTV,"Bravia X90K XR-65X90K.jpeg"))),
(85, LOAD_FILE(CONCAT(@UploadsTV,"Bravia A80K XR-55A80K.jpeg"))),
(86, LOAD_FILE(CONCAT(@UploadsTV,"Bravia X75K KD-43X75K.jpeg"))),
(87, LOAD_FILE(CONCAT(@UploadsTV,"Bravia A95K XR-55A95K.jpeg"))),
(88, LOAD_FILE(CONCAT(@UploadsTV,"KD-55X81J.jpeg"))),
(89, LOAD_FILE(CONCAT(@UploadsTV,"X85TK KD-65X85TK.jpeg"))),
(90, LOAD_FILE(CONCAT(@UploadsTV,"KD-55X80J.jpeg"))),
(91, LOAD_FILE(CONCAT(@UploadsTV,"X85TK KD-43X85K.jpeg"))),
(92, LOAD_FILE(CONCAT(@UploadsTV,"Bravia X81K KD-55X81K.jpeg"))),
(93, LOAD_FILE(CONCAT(@UploadsTV,"KD-85X85TJ.jpeg"))),
(94, LOAD_FILE(CONCAT(@UploadsTV,"XR85Z9JCEP.jpeg"))),
(95, LOAD_FILE(CONCAT(@Uploads,"audiospeakers.jpeg"))),
(96, LOAD_FILE(CONCAT(@UploadsAS,"SRS-XP500.jpeg"))),
(97, LOAD_FILE(CONCAT(@UploadsAS,"SRS-XG500.jpeg"))),
(98, LOAD_FILE(CONCAT(@UploadsAS,"SRS-XB13.jpeg"))),
(99, LOAD_FILE(CONCAT(@UploadsAS,"SRS-XB33.jpeg"))),
(100, LOAD_FILE(CONCAT(@UploadsAS,"SRS-XE300.jpeg"))),
(101, LOAD_FILE(CONCAT(@UploadsAS,"SRS-XG300.jpeg"))),
(102, LOAD_FILE(CONCAT(@UploadsAS,"SRS-XB23.jpeg"))),
(103, LOAD_FILE(CONCAT(@UploadsAS,"SRS-NB10.jpeg"))),
(104, LOAD_FILE(CONCAT(@UploadsAS,"SRS-RA3000.jpeg"))),
(105, LOAD_FILE(CONCAT(@UploadsAS,"SRS-XE200.jpeg"))),
(106, LOAD_FILE(CONCAT(@UploadsAS,"SRS-XV900.jpeg"))),
(107, LOAD_FILE(CONCAT(@UploadsAS,"MHC-V13.jpeg"))),
(108, LOAD_FILE(CONCAT(@UploadsAS,"MHC-V02.jpeg"))),
(109, LOAD_FILE(CONCAT(@UploadsAS,"SRS-XP700.jpeg"))),
(110, LOAD_FILE(CONCAT(@UploadsAS,"MHC-V43D.jpeg"))),
(111, LOAD_FILE(CONCAT(@UploadsAS,"SHAKE-X30D.jpeg"))),
(112, LOAD_FILE(CONCAT(@Uploads,"microphone.jpeg"))),
(113, LOAD_FILE(CONCAT(@UploadsMicrophone,"ECM-CG60.jpeg"))),
(114, LOAD_FILE(CONCAT(@UploadsMicrophone,"ECM-AW4.jpeg"))),
(115, LOAD_FILE(CONCAT(@UploadsMicrophone,"ECM-W2BT.jpeg"))),
(116, LOAD_FILE(CONCAT(@UploadsMicrophone,"F-112.jpeg"))),
(117, LOAD_FILE(CONCAT(@UploadsMicrophone,"ECM-VG1.jpeg"))),
(118, LOAD_FILE(CONCAT(@UploadsMicrophone,"ECM-B1M.jpeg"))),
(119, LOAD_FILE(CONCAT(@UploadsMicrophone,"ECM-GZ1M.jpeg"))),
(120, LOAD_FILE(CONCAT(@UploadsMicrophone,"ECM-W1M.jpeg"))),
(121, LOAD_FILE(CONCAT(@UploadsMicrophone,"ECM-XYST1M.jpeg"))),
(122, LOAD_FILE(CONCAT(@Uploads,"video.jpg"))),
(123, LOAD_FILE(CONCAT(@UploadsVideo,"FX3.jpeg"))),
(124, LOAD_FILE(CONCAT(@UploadsVideo,"FDR-AX700.jpeg"))),
(125, LOAD_FILE(CONCAT(@UploadsVideo,"FDR-AX53.jpeg"))),
(126, LOAD_FILE(CONCAT(@UploadsVideo,"FDR-AX43B.jpeg"))),
(127, LOAD_FILE(CONCAT(@UploadsVideo,"PXW-FS5M2.jpeg"))),
(128, LOAD_FILE(CONCAT(@UploadsVideo,"HDR-CX405B.jpg"))),
(129, LOAD_FILE(CONCAT(@UploadsVideo,"HDR-CX625.jpg"))),
(130, LOAD_FILE(CONCAT(@UploadsVideo,"FX6 Kit 24-105mm.jpeg"))),
(131, LOAD_FILE(CONCAT(@UploadsVideo,"FX6.jpeg"))),
(132, LOAD_FILE(CONCAT(@UploadsVideo,"PXW-Z90.jpeg"))),
(133, LOAD_FILE(CONCAT(@Uploads,"gameconsole.jpeg")));

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
       (6, "Memory card", "memcard", 1, 6),
       (7, "Audio speakers", "audiospeakers", 1, 95),
       (8, "Microphone", "microphone", 1, 112),
       (9, "Video", "video", 1, 122),
       (10, "Game console", "game", 1, 133);

-- add products
DELETE FROM sonyshop.products WHERE id >= 0;
INSERT INTO sonyshop.products(name, description, price, year, quantity, category_id, image_id)
VALUES ("Sony Xperia 10 II XQ-AU52 Dual", "Android, экран 6\" OLED (1080x2520), 
Qualcomm Snapdragon 665, ОЗУ 4 ГБ, флэш-память 128 ГБ, карты памяти, камера 12 Мп, 
аккумулятор 3600 мАч, 2 SIM", 899, 2020, 24, 1, 7),
("Sony Xperia 1 II XQ-AT52", "Android, экран 6.5\" OLED (1644x3840), Qualcomm Snapdragon 865, 
ОЗУ 12 ГБ, флэш-память 256 ГБ, карты памяти, камера 12 Мп, аккумулятор 4000 мАч, 2 SIM", 
2679, 2020, 19, 1, 8),
("Sony Xperia 5 II Dual", "Android, экран 6.1\" OLED (1080x2520), Qualcomm Snapdragon 865, 
ОЗУ 8 ГБ, флэш-память 128 ГБ, карты памяти, камера 12 Мп, аккумулятор 4000 мАч, 2 SIM", 
2250, 2020, 43, 1, 9),
("Sony Xperia 1", "Android, экран 6.5\" OLED (1644x3840), Qualcomm Snapdragon 855, ОЗУ 6 ГБ, 
флэш-память 64 ГБ, карты памяти, камера 12 Мп, аккумулятор 3330 мАч, 1 SIM", 1450, 2019, 39, 1, 10),
("Sony Xperia 5 J9210", "Android, экран 6.1\" OLED (1080x2520), Qualcomm Snapdragon 855, 
ОЗУ 6 ГБ, флэш-память 128 ГБ, карты памяти, камера 12 Мп, аккумулятор 3140 мАч, 2 SIM", 
1699, 2019, 28, 1, 11),
("Sony Xperia 10 I3113 Dual", "Android, экран 6\" IPS (1080x2520), Qualcomm Snapdragon 630, 
ОЗУ 3 ГБ, флэш-память 64 ГБ, карты памяти, камера 13 Мп, аккумулятор 2870 мАч, 2 SIM", 
699, 2019, 54, 1, 12),
("Sony Xperia L3 I4312 Dual", "Android, экран 5.7\" IPS (720x1440), Mediatek MT6762 Helio P22, 
ОЗУ 3 ГБ, флэш-память 32 ГБ, карты памяти, камера 13 Мп, аккумулятор 3300 мАч, 2 SIM", 
499, 2019, 38, 1, 13),
("Sony Xperia 10 Plus I4293 Dual", "Android, экран 6.5\" IPS (1080x2520), Qualcomm Snapdragon 636, 
ОЗУ 6 ГБ, флэш-память 64 ГБ, карты памяти, камера 12 Мп, аккумулятор 3000 мАч, 2 SIM", 
939, 2019, 25, 1, 14),
("Sony Xperia XZ2 Dual", "Android, экран 5.7\" IPS (1080x2160), Qualcomm Snapdragon 845, ОЗУ 4 ГБ, 
флэш-память 64 ГБ, карты памяти, камера 19 Мп, аккумулятор 3180 мАч, 2 SIM", 1100, 2018, 32, 1, 15),
("Sony Xperia XA1 Plus Dual", "Android, экран 5.5\" IPS (1080x1920), Mediatek MT6757 Helio P20, 
ОЗУ 4 ГБ, флэш-память 32 ГБ, карты памяти, камера 23 Мп, аккумулятор 3430 мАч, 2 SIM", 535, 2017, 7, 1, 16),
("Sony Xperia L1 Dual", "Android, экран 5.5\" IPS (720x1280), Mediatek MT6737T, ОЗУ 2 ГБ, 
флэш-память 16 ГБ, карты памяти, камера 13 Мп, аккумулятор 2620 мАч, 2 SIM", 294, 2017, 22, 1, 17),
("Sony Walkman NW-WS623", "BT, время работы 12 часов", 382, 2017, 34, 2, 18),
("Sony NW-WS413", "Время работы 12 часов", 359, 2016, 16, 2, 19),
("Sony NWZ-B183F", "Экран 0.9\" OLED 128 x 36, радио, время работы 20 часов", 175, 2014, 53, 2, 20),
("Sony WH-1000XM4", "Беспроводные наушники с микрофоном, мониторные (охватывающие), портативные, 
Bluetooth 5.0, NFC, 4-40000 Гц, поворотные чашки/складное оголовье, быстрая зарядка, 
время работы 38 ч, кабель 1.2 м", 845, 2020, 37, 3, 21),
("Sony WF-XB700", "Беспроводные наушники с микрофоном, внутриканальные, портативные, Bluetooth 5.0, 
20-20000 Гц, быстрая зарядка, время работы 9 ч", 209, 2020, 34, 3, 22),
("Sony WF-1000XM3", "Беспроводные наушники с микрофоном, внутриканальные, портативные, Bluetooth 5.0, 
NFC, 20-20000 Гц, время работы 8 ч", 442, 2019, 37, 3, 23),
("Sony WI-C200", "Беспроводные наушники с микрофоном, внутриканальные, портативные, Bluetooth 5.0, 
20-20000 Гц, магниты, быстрая зарядка, время работы 15 ч", 63, 2019, 45, 3, 24),
("Sony PS5 Pulse 3D", "Беспроводные наушники с микрофоном, мониторные (охватывающие), геймерские, 
время работы 12 ч", 315, 2020, 20, 3, 25),
("Sony WI-XB400", "Беспроводные наушники с микрофоном, внутриканальные, портативные, Bluetooth 5.0, 
20-20000 Гц, магниты, быстрая зарядка, время работы 15 ч", 95, 2019, 28, 3, 26),
("Sony WH-CH510", "Беспроводные наушники с микрофоном, накладные, портативные, Bluetooth 5.0, 
20-20000 Гц, поворотные чашки, быстрая зарядка, время работы 35 ч", 89, 2019, 28, 3, 27),
("Sony WI-SP510", "Беспроводные наушники с микрофоном, внутриканальные, портативные/спортивные, 
Bluetooth 5.0, 20-20000 Гц, магниты, быстрая зарядка, время работы 15 ч", 148, 2020, 26, 3, 28),
("Sony WH-CH710N", "Беспроводные наушники с микрофоном, мониторные (охватывающие), портативные, 
Bluetooth 5.0, NFC, 7-20000 Гц, поворотные чашки, быстрая зарядка, время работы 35 ч, кабель 1.2 м", 
255, 2020, 19, 3, 29),
("Sony Gold Wireless", "Беспроводные наушники с микрофоном, мониторные (охватывающие), геймерские, 
время работы 7 ч, кабель 1.2 м", 230, 2014, 23, 3, 30),
("Sony MDR-EX155AP", "Наушники с микрофоном, внутриканальные, портативные, 5-24000 Гц, кабель 1.2 м", 
40, 2017, 48, 3, 31),
("Sony MDR-EX255AP", "Наушники с микрофоном, внутриканальные, портативные, 5-25000 Гц, кабель 1.2 м", 
57, 2017, 44, 3, 32),
("Sony KD-55XH9505", "54.6\" 3840x2160 (4K UHD), матрица VA, частота матрицы 120 Гц, 
Smart TV (Android TV), HDR, Wi-Fi", 3300, 2020, 15, 4, 33),
("Sony KD-55XH9096", "54.6\" 3840x2160 (4K UHD), матрица VA, частота матрицы 120 Гц, 
Smart TV (Android TV), HDR, Wi-Fi", 3090, 2020, 12, 4, 34),
("Sony KD-65A8", "65\" 3840x2160 (4K UHD), матрица OLED, частота матрицы 120 Гц, 
Smart TV (Android TV), HDR, Wi-Fi", 5999, 2020, 8, 4, 35),
("Sony KD-43XH8005", "42.5\" 3840x2160 (4K UHD), частота матрицы 60 Гц, Smart TV (Android TV), 
HDR, Wi-Fi", 1800, 2020, 13, 4, 36),
("Sony KD-49XH8596", "48.5\" 3840x2160 (4K UHD), частота матрицы 120 Гц, Smart TV (Android TV), 
HDR, Wi-Fi", 2568, 2020, 15, 4, 37),
("Sony KD-43XH8096", "48.5\" 3840x2160 (4K UHD), частота матрицы 60 Гц, Smart TV (Android TV), 
HDR, Wi-Fi", 1900, 2020, 19, 4, 38),
("Sony KD-55XG9505", "54.6\" 3840x2160 (4K UHD), матрица VA, частота матрицы 120 Гц, 
Smart TV (Android TV), HDR, Wi-Fi", 2750, 2019, 8, 4, 39),
("Sony KD-75ZH8", "75\" 7680x4320 (8K UHD), частота матрицы 120 Гц, Smart TV (Android TV), 
HDR, Wi-Fi", 14500, 2020, 2, 4, 40),
("Sony KD-43XG8096", "42.5\" 3840x2160 (4K UHD), частота матрицы 60 Гц, Smart TV (Android TV), 
HDR, Wi-Fi", 1830, 2019, 15, 4, 41),
("Sony KD-85ZG9", "84.6\" 7680x4320 (8K UHD), матрица VA, частота матрицы 120 Гц, 
Smart TV (Android TV), HDR, Wi-Fi", 25499, 2019, 5, 4, 42),
("Sony KD-65AG9", "65\" 3840x2160 (4K UHD), матрица OLED, частота матрицы 120 Гц, 
Smart TV (Android TV), HDR, Wi-Fi", 6999, 2019, 10, 4, 43),
("Sony KD-55XF9005", "54.6\" 3840x2160 (4K UHD), матрица VA, частота матрицы 120 Гц, 
Smart TV (Android TV), HDR, Wi-Fi", 2599, 2018, 13, 4, 44),
("Sony Alpha a7 III Body", "Беззеркальная камера, матрица Full frame (полный кадр) 24.2 Мп, 
без объектива (body), Wi-Fi", 4700, 2018, 19, 5, 45),
("Sony Alpha a6000 Kit 16-50mm", "Беззеркальная камера, матрица APS-C (1.5 crop) 24.3 Мп, 
с объективом F3.5-5.6 16-50 мм, Wi-Fi", 1619, 2014, 22, 5, 46),
("Sony Alpha a6600 Body", "Беззеркальная камера, матрица APS-C (1.5 crop) 24.2 Мп, 
без объектива (body), Wi-Fi", 2598, 2019, 20, 5, 47),
("Sony Alpha a6400 Body", "Беззеркальная камера, матрица APS-C (1.5 crop) 24.2 Мп, 
без объектива (body), Wi-Fi", 2580, 2019, 19, 5, 48),
("Sony Alpha a7C Body", "Беззеркальная камера, матрица Full frame (полный кадр) 24.2 Мп, 
без объектива (body), Wi-Fi", 5280, 2020, 21, 5, 49),
("Sony Cyber-shot DSC-RX100", "Компакт-камера, матрица 1\" 20.2 Мп, объектив 3.6X F1.8-4.9 28-100 мм", 
1099, 2012, 28, 5, 50),
("Sony SDXC SF-E128 128GB", "128 ГБ, класс 10, UHS-II (класс U3), V60, чтение: 270 МБ/с, 
запись: 120 МБ/с", 220, null, 42, 6, 51),
("Sony XQD G Series 64GB", "64 ГБ, чтение: 440 МБ/с, запись: 400 МБ/с", 570, null, 31, 6, 52),
("Sony SF-M Tough SDXC 256GB", "256 Гб, класс 10, UHS-II (класс U3), V60, чтение: 277 МБ/с, 
запись: 150 МБ/с", 757, null, 25, 6, 53),
("Sony SDXC SF-M Series UHS-II 128GB", "128 ГБ, класс 10, UHS-II (класс U3), чтение: 260 МБ/с, 
запись: 100 МБ/с", 237, null, 34, 6, 54),
("Sony Memory Stick PRO Duo MSX-M8GST 8GB", "8 ГБ, запись: 4 МБ/с", 10, null, 124, 6, 55),
("Sony XR-55A90J", "55\" 3840x2160 (4K UHD), матрица OLED, частота матрицы 120 Гц, HDR, Wi-Fi", 
8675, 2021, 9, 4, 56),
("Sony XR-65X95J", "65\" 3840x2160 (4K UHD), матрица VA, частота матрицы 120 Гц, 
Smart TV (Android TV), HDR, Wi-Fi", 8500, 2021, 11, 4, 57),
("Sony XR-55A80J", "55\" 3840x2160 (4K UHD), матрица OLED, частота матрицы 120 Гц, 
Smart TV (Android TV), HDR, Wi-Fi", 8205, 2021, 14, 4, 58),
("Sony KD-43XG7005", "42.5\" 3840x2160 (4K UHD), частота матрицы 60 Гц, Smart TV (Linux), 
HDR, Wi-Fi", 1653, 2019, 21, 4, 59),
("Sony KDL-43WF665", "43\" 1920x1080 (Full HD), частота матрицы 60 Гц, Smart TV (Linux), 
HDR, Wi-Fi", 1193, 2018, 20, 4, 60),
("Sony KD-65XF7596", "65\" 3840x2160 (4K UHD), частота матрицы 60 Гц, Smart TV (Android TV), 
HDR, Wi-Fi", 2700, 2018, 16, 4, 61),
("Sony KDL-32WE613", "32\" 1366x768 (HD), частота матрицы 60 Гц, Smart TV (Linux), Wi-Fi", 
987, 2017, 33, 4, 62),
("Sony KDL-40WD653", "40\" 1920x1080 (Full HD), матрица VA, частота матрицы 60 Гц, 
Smart TV (Opera), Wi-Fi", 1228, 2017, 15, 4, 63),
("Sony Xperia 10 IV XQ-CC54", "Android (без оболочки), экран 6\" OLED (1080x2520) 60 Гц, 
Qualcomm Snapdragon 695, ОЗУ 6 ГБ, память 128 ГБ, поддержка карт памяти, камера 12 Мп, 
аккумулятор 5000 мАч, 2 SIM (nano-SIM/eSIM), влагозащита IP68", 1199, 2022, 23, 1, 64),
("Sony Xperia 1 IV XQ-CT72", "Android, экран 6.5\" OLED (1644x3840) 120 Гц, 
Qualcomm Snapdragon 8 Gen1 SM8450, ОЗУ 12 ГБ, память 512 ГБ, поддержка карт памяти, камера 12 Мп, 
аккумулятор 5000 мАч, 2 SIM (nano-SIM), влагозащита IP68", 3090, 2022, 16, 1, 65),
("Sony Xperia 10 III XQ-BT52", "Android, экран 6\" OLED (1080x2520) 60 Гц, Qualcomm Snapdragon 690, 
ОЗУ 6 ГБ, память 128 ГБ, поддержка карт памяти, камера 12 Мп, аккумулятор 4500 мАч, 2 SIM (nano-SIM), 
влагозащита IP68", 1000, 2021, 27, 1, 66),
("Sony Xperia 5 III XQ-BQ72", "Android (без оболочки), экран 6.1\" OLED (1080x2520) 120 Гц, 
Qualcomm Snapdragon 888, ОЗУ 8 ГБ, память 256 ГБ, поддержка карт памяти, камера 12 Мп, 
аккумулятор 4500 мАч, 2 SIM (nano-SIM), влагозащита IPX8", 2250, 2021, 20, 1, 67),
("Sony Xperia 1 III XQ-BC72", "Android (без оболочки), экран 6.5\" OLED (1644x3840) 120 Гц, 
Qualcomm Snapdragon 888, ОЗУ 12 ГБ, память 512 ГБ, поддержка карт памяти, камера 12 Мп, 
аккумулятор 4500 мАч, 2 SIM (nano-SIM), влагозащита IP68", 3351, 2021, 14, 1, 68),
("Sony Xperia 5 IV", "Android (без оболочки), экран 6.1\" OLED (1080x2520) 120 Гц, 
Qualcomm Snapdragon 8 Gen1 SM8450, ОЗУ 8 ГБ, память 256 ГБ, поддержка карт памяти, 
камера 12 Мп, аккумулятор 5000 мАч, 2 SIM (nano-SIM/eSIM), влагозащита IP68", 2790, 2022, 15, 1, 69),
("Sony Xperia 10 II XQ-AU52", "Android (без оболочки), экран 6\" OLED (1080x2520) 60 Гц, 
Qualcomm Snapdragon 665, ОЗУ 4 ГБ, память 128 ГБ, поддержка карт памяти, камера 12 Мп, 
аккумулятор 3600 мАч, 2 SIM (nano-SIM), влагозащита IP68", 1030, 2020, 19, 1, 70),
("Sony Xperia Ace III A203SO", "Android, экран 5.5\" IPS (720x1496) 60 Гц, 
Qualcomm Snapdragon 480 5G, ОЗУ 4 ГБ, память 64 ГБ, поддержка карт памяти, камера 13 Мп, 
аккумулятор 4500 мАч, 2 SIM (nano-SIM/eSIM), влагозащита IPX8", 920, 2022, 11, 1, 71),
("Sony WH-1000XM5", "беспроводные наушники с микрофоном, мониторные (охватывающие), 
портативные, Bluetooth 5.2, 4-40000 Гц, поворотные чашки/складное оголовье, быстрая зарядка, 
время работы 40 ч, кабель 1.2 м, активное шумоподавление", 1130, 2022, 27, 3, 72),
("Sony WF-1000XM4", "беспроводные наушники с микрофоном, внутриканальные, портативные/спортивные, 
Bluetooth 5.2, 20-40000 Гц, быстрая зарядка, время работы 12 ч, с кейсом 36 ч, активное шумоподавление", 
599, 2021, 32, 3, 73),
("Sony WH-CH520", "беспроводные наушники с микрофоном, накладные, портативные, Bluetooth 5.2, 
20-20000 Гц, поворотные чашки, быстрая зарядка, время работы 50 ч", 289, 2023, 22, 3, 74),
("Sony MDR7506", "наушники, мониторные (охватывающие), профессиональные, 10-20000 Гц, поворотные чашки, 
кабель 3 м", 440, 2014, 20, 3, 75),
("Sony WH-XB910N", "беспроводные наушники с микрофоном, мониторные (охватывающие), портативные, 
Bluetooth 5.2, 7-25000 Гц, поворотные чашки/складное оголовье, быстрая зарядка, время работы 50 ч, 
кабель 1.2 м, активное шумоподавление", 650, 2022, 19, 3, 76),
("Sony MDR-XD150", "наушники, мониторные (охватывающие), профессиональные, 12-22000 Гц, кабель 2 м", 
45, 2013, 31, 3, 77),
("Sony LinkBuds WF-L900", "беспроводные наушники с микрофоном, вставные, портативные, Bluetooth 5.2, 
20-20000 Гц, быстрая зарядка, время работы 5.5 ч, с кейсом 17.5 ч", 478, 2022, 13, 3, 78),
("Sony MDR-XB55AP", "наушники с микрофоном, внутриканальные, портативные, 4-24000 Гц, кабель 1.2 м", 
129, 2022, 61, 3, 79),
("Sony microSDHC (Class 10) 8GB", "8 ГБ, класс 10, UHS-I (класс U1), чтение: 90 МБ/с", 18, null, 25, 6, 80),
("Sony microSDHC (Class 10) 32GB + адаптер (SR32NYAT)", "32 ГБ, класс 10, чтение: 40 МБ/с, адаптер", 
24, null, 35, 6, 81),
("Sony microSDXC UHS-I (Class 10) 64GB", "64 ГБ, класс 10, UHS-I (класс U1), чтение: 40 МБ/с", 
45, null, 41, 6, 82),
("Sony XR-55X90J", "55\" 3840x2160 (4K UHD), матрица VA, частота матрицы 120 Гц, Smart TV (Android TV), 
HDR, Wi-Fi", 3180, 2021, 17, 4, 83),
("Sony Bravia X90K XR-65X90K", "65\" 3840x2160 (4K UHD), частота матрицы 120 Гц, Smart TV (Android TV), 
HDR, Wi-Fi", 4627, 2022, 15, 4, 84),
("Sony Bravia A80K XR-55A80K", "55\" 3840x2160 (4K UHD), матрица OLED, частота матрицы 120 Гц, 
Smart TV (Android TV), HDR, Wi-Fi", 4899, 2022, 17, 4, 85),
("Sony Bravia X75K KD-43X75K", "43\" 3840x2160 (4K UHD), частота матрицы 60 Гц, Smart TV (Google TV), 
HDR, Wi-Fi", 2159, 2022, 22, 4, 86),
("Sony Bravia A95K XR-55A95K", "55\" 3840x2160 (4K UHD), матрица OLED, частота матрицы 120 Гц, 
Smart TV (Android TV), HDR, Wi-Fi", 7899, 2022, 11, 4, 87),
("Sony KD-55X81J", "55\" 3840x2160 (4K UHD), частота матрицы 60 Гц, Smart TV (Android TV), HDR, Wi-Fi", 
2349, 2021, 16, 4, 88),
("Sony X85TK KD-65X85TK", "65\" 3840x2160 (4K UHD), экстерьерный, частота матрицы 120 Гц, 
Smart TV (Android TV), HDR, Wi-Fi", 3750, 2022, 14, 4, 89),
("Sony KD-55X80J", "55\" 3840x2160 (4K UHD), матрица IPS, частота матрицы 60 Гц, Smart TV (Android TV), 
HDR, Wi-Fi", 2758, 2021, 16, 4, 90),
("Sony X85TK KD-43X85K", "43\" 3840x2160 (4K UHD), экстерьерный, частота матрицы 120 Гц, 
Smart TV (Android TV), HDR, Wi-Fi", 2899, 2022, 16, 4, 91),
("Sony Bravia X81K KD-55X81K", "55\" 3840x2160 (4K UHD), экстерьерный, матрица IPS, 
частота матрицы 60 Гц, Smart TV (Android TV), HDR, Wi-Fi", 2999, 2022, 19, 4, 92),
("Sony KD-85X85TJ", "85\" 3840x2160 (4K UHD), частота матрицы 120 Гц, индекс динамичных сцен 1000, 
Smart TV (Android TV), HDR, Wi-Fi", 8900, 2021, 10, 4, 93),
("Sony XR85Z9JCEP", "85\" 7680x4320 (8K UHD), частота матрицы 60 Гц, Smart TV (Android TV), 
HDR, Wi-Fi", 29000, 2021, 7, 4, 94),
("Sony SRS-XP500", "воспроизведение с USB, питание от аккумулятора, BT, AUX, время работы: 20 ч.", 
1388, 2021, 20, 7, 96),
("Sony SRS-XG500", "воспроизведение с USB, питание от аккумулятора, BT 5.0, AUX, влагозащита IP66, 
время работы: 30 ч.", 1158, 2021, 22, 7, 97),
("Sony SRS-XB13", "питание от аккумулятора, BT 4.2, влагозащита IP67, время работы: 16 ч.", 
182, 2021, 35, 7, 98),
("Sony SRS-XB33", "питание от аккумулятора, BT 5.0, NFC, влагозащита IP67, время работы: 24 ч.", 
609, 2020, 22, 7, 99),
("Sony SRS-XE300", "питание от аккумулятора, BT 5.2, влагозащита IP67, время работы: 24 ч.", 
612, 2022, 24, 7, 100),
("Sony SRS-XG300", "питание от аккумулятора, BT 5.2, AUX, влагозащита IP67, время работы: 25 ч.", 
949, 2022, 19, 7, 101),
("Sony SRS-XB23", "питание от аккумулятора, BT 5.0, влагозащита IP67, время работы: 12 ч.", 
319, 2020, 39, 7, 102),
("Sony SRS-NB10", "питание от аккумулятора, BT 5.1, влагозащита IPX4, время работы: 20 ч.", 
672, null, 26, 7, 103),
("Sony SRS-RA3000", "питание от сети, BT 4.2, Wi-Fi, AUX", 1221, null, 18, 7, 104),
("Sony SRS-XE200", "питание от аккумулятора, BT 5.2, влагозащита IP67, время работы: 16 ч.", 
614, 2022, 23, 7, 105),
("Sony SRS-XV900", "воспроизведение с USB, питание от сети и аккумулятора, BT 5.2, AUX, 
время работы: 25 ч.", 5914, null, 10, 7, 106),
("Sony MHC-V13", "воспроизведение с USB/CD, питание от сети, BT 4.2, AUX", 992, 2020, 14, 7, 107),
("Sony MHC-V02", "воспроизведение с USB/CD, питание от сети, BT 4.2, AUX", 979, 2019, 12, 7, 108),
("Sony SRS-XP700", "воспроизведение с USB, питание от сети и аккумулятора, BT, AUX, время работы: 25 ч.", 
1770, 2021, 15, 7, 109),
("Sony MHC-V43D", "воспроизведение с USB/DVD, питание от сети, BT 4.2, AUX", 1499, 2020, 16, 7, 110),
("Sony SHAKE-X30D", "микро-система, стерео, воспроизведение DVD/USB, радио, Bluetooth", 
2788, null, 8, 7, 111),
("Sony ECM-CG60", "электретный (малый конденсатор), кардиоидная направленность, 40-20000 Гц, 
разъем подключения 3.5 мм, длина кабеля 0.35 м", 449, null, 28, 8, 113),
("Sony ECM-AW4", "электретный (малый конденсатор), 300-9000 Гц, разъем подключения отсутствует 
(нужен кабель), длина кабеля 50 м", 563, null, 23, 8, 114),
("Sony ECM-W2BT", "для интервью и репортажей, круговая направленность, 20-20000 Гц, 
радиус действия 200 м, время работы до 3 ч", 830, null, 19, 8, 115),
("Sony F-112", "динамический, ручной, для интервью и репортажей, круговая направленность, 60-17000 Гц, 
разъем подключения отсутствует (нужен кабель)", 850, null, 22, 8, 116),
("Sony ECM-VG1", "конденсаторный, микрофон-пушка, для интервью и репортажей, 
суперкардиоидная направленность, 40-20000 Гц, разъем подключения отсутствует (нужен кабель)", 
899, null, 19, 8, 117),
("Sony ECM-B1M", "накамерный, для интервью и репортажей, кардиоидная/круговая/суперкардиоидная 
направленность, разъем подключения мультиинтерфейсный разъем Sony", 1230, null, 17, 8, 118),
("Sony ECM-GZ1M", "накамерный/микрофон-пушка, для интервью и репортажей, гиперкардиоидная направленность, 
150-15000 Гц, разъем подключения мультиинтерфейсный разъем Sony", 360, null, 28, 8, 119),
("Sony ECM-W1M", "электретный (малый конденсатор), 300-9000 Гц, длина кабеля 100 м", 425, null, 25, 8, 120),
("Sony ECM-XYST1M", "электретный (малый конденсатор), стерео направленность, 70-20000 Гц, 
разъем подключения 3.5 мм", 499, null, 22, 8, 121),
("Sony FX3", "разрешения видеосъемки: 4K, матрица CMOS, 10.2 Мп, сжатие RAW/XAVC, байонет Sony E, 
экран 3\"", 12990, 2021, 11, 9, 123),
("Sony FDR-AX700", "разрешения видеосъемки: 4K, матрица CMOS, 14.2 Мп, сжатие XAVC/AVCHD/MPEG-4 (MP4), 
носитель: карта памяти, объектив F2.8-4.5, экран 3.5\"", 5890, 2017, 12, 9, 124),
("Sony FDR-AX53", "разрешения видеосъемки: 4K, матрица CMOS, 8.3 Мп, носитель: карта памяти, 
объектив F2-3.8, экран 3\"", 3199, 2016, 14, 9, 125),
("Sony FDR-AX43B", "разрешения видеосъемки: 4K, матрица CMOS, 8.29 Мп, сжатие AVCHD/H.264/H.265 
(HEVC)/MPEG-4 (MP4)/XAVC, носитель: карта памяти, объектив F2-3.8, экран 3\"", 2599, null, 13, 9, 126),
("Sony PXW-FS5M2", "разрешения видеосъемки: 4K, матрица CMOS, сжатие XAVC/AVCHD, носитель: карта памяти, 
экран 3.5\"", 23879, 2019, 6, 9, 127),
("Sony HDR-CX405B", "разрешения видеосъемки: HD, матрица CMOS, 2.29 Мп, сжатие AVCHD/MPEG-4 (MP4), 
носитель: карта памяти, объектив F1.8-4, экран 2.7\"", 1200, null, 18, 9, 128),
("Sony HDR-CX625", "разрешения видеосъемки: HD, 2.29 Мп, сжатие XAVC/MPEG-4 (MP4), 
носитель: карта памяти, объектив F1.8-4, экран 3\"", 1990, 2016, 15, 9, 129),
("Sony FX6 Kit 24-105mm", "разрешения видеосъемки: 4K, матрица BSI-CMOS, 10.2 Мп, сжатие RAW/XAVC, 
объектив F4, динамический диапазон 15 ступеней, байонет Sony E, экран 3.5\"", 25590, 2020, 5, 9, 130),
("Sony FX6", "разрешения видеосъемки: 4K, матрица BSI-CMOS, 10.2 Мп, сжатие RAW/XAVC, 
динамический диапазон 15 ступеней, байонет Sony E, экран 3.5\"", 22590, 2020, 3, 9, 131),
("Sony PXW-Z90", "разрешения видеосъемки: 4K, матрица CMOS, 14.2 Мп, сжатие XAVC/AVCHD/MPEG-2, 
носитель: карта памяти, объектив F2.8-4.5, экран 3.5\"", 10390, 2017, 8, 9, 132);