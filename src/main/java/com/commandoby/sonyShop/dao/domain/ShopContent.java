package com.commandoby.sonyShop.dao.domain;

import com.commandoby.sonyShop.exceptions.NoFoundException;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class ShopContent {
    private static List<User> userList = new ArrayList<>();
    private static final List<Category> categoriesList = new ArrayList<>();
    private static final List<Product> productList = new ArrayList<>();
    private static final Category phone = new Category("Phone", "phone", "phone.jpeg", 0);
    private static final Category mp3Player = new Category("MP3 player", "player", "player.jpeg", 0);
    private static final Category headphones = new Category("Headphones", "headphones", "headphones.jpeg", 0);
    private static final Category TV = new Category("TV", "tv", "tv.jpeg", 0);
    private static final Category photo = new Category("Photo", "photo", "photo.jpeg", 0);
    private static final Category memoryCard = new Category("Memory card", "memcard", "memcard.jpeg", 0);

    static {
        userList.add(new User("Admin", "Admin", "admin", "admin", "1980-01-01", 9999999));

        categoriesList.add(phone);
        categoriesList.add(mp3Player);
        categoriesList.add(headphones);
        categoriesList.add(TV);
        categoriesList.add(photo);
        categoriesList.add(memoryCard);

        productList.add(new Product("Sony Xperia 10 II XQ-AU52 Dual", "10_II_XQ-AU52_Dual.jpeg",
                 "Android, экран 6\" OLED (1080x2520), Qualcomm Snapdragon 665, ОЗУ 4 ГБ, " +
                "флэш-память 128 ГБ, карты памяти, камера 12 Мп, аккумулятор 3600 мАч, 2 SIM", phone, 899, 0));
        productList.add(new Product("Sony Xperia 1 II XQ-AT52", "1_II_XQ-AT52.jpeg",
                 "Android, экран 6.5\" OLED (1644x3840), Qualcomm Snapdragon 865, ОЗУ 12 ГБ, " +
                "флэш-память 256 ГБ, карты памяти, камера 12 Мп, аккумулятор 4000 мАч, 2 SIM", phone, 2679, 0));
        productList.add(new Product("Sony Xperia 5 II Dual", "5_II_Dual.jpeg",
                 "Android, экран 6.1\" OLED (1080x2520), Qualcomm Snapdragon 865, ОЗУ 8 ГБ, " +
                "флэш-память 128 ГБ, карты памяти, камера 12 Мп, аккумулятор 4000 мАч, 2 SIM", phone, 2250, 0));
        productList.add(new Product("Sony Xperia 1", "1.jpeg",
                 "Android, экран 6.5\" OLED (1644x3840), Qualcomm Snapdragon 855, ОЗУ 6 ГБ, " +
                "флэш-память 64 ГБ, карты памяти, камера 12 Мп, аккумулятор 3330 мАч, 1 SIM", phone, 1450, 0));
        productList.add(new Product("Sony Xperia 5 J9210", "5_J9210.jpeg",
                 "Android, экран 6.1\" OLED (1080x2520), Qualcomm Snapdragon 855, ОЗУ 6 ГБ, " +
                "флэш-память 128 ГБ, карты памяти, камера 12 Мп, аккумулятор 3140 мАч, 2 SIM", phone, 1699, 0));
        productList.add(new Product("Sony Xperia 10 I3113 Dual", "10_I3113_Dual.jpeg",
                 "Android, экран 6\" IPS (1080x2520), Qualcomm Snapdragon 630, ОЗУ 3 ГБ, " +
                "флэш-память 64 ГБ, карты памяти, камера 13 Мп, аккумулятор 2870 мАч, 2 SIM", phone, 699, 0));
        productList.add(new Product("Sony Xperia L3 I4312 Dual", "L3_I4312_Dual.jpeg",
                "Android, экран 5.7\" IPS (720x1440), Mediatek MT6762 Helio P22, ОЗУ 3 ГБ, " +
                "флэш-память 32 ГБ, карты памяти, камера 13 Мп, аккумулятор 3300 мАч, 2 SIM", phone, 499, 0));
        productList.add(new Product("Sony Xperia 10 Plus I4293 Dual", "10_Plus_I4293_Dual.jpeg",
                "Android, экран 6.5\" IPS (1080x2520), Qualcomm Snapdragon 636, ОЗУ 6 ГБ, " +
                "флэш-память 64 ГБ, карты памяти, камера 12 Мп, аккумулятор 3000 мАч, 2 SIM", phone, 939, 0));
        productList.add(new Product("Sony Xperia XZ2 Dual", "XZ2_Dual.jpeg",
                "Android, экран 5.7\" IPS (1080x2160), Qualcomm Snapdragon 845, ОЗУ 4 ГБ, " +
                "флэш-память 64 ГБ, карты памяти, камера 19 Мп, аккумулятор 3180 мАч, 2 SIM", phone, 1100, 0));
        productList.add(new Product("Sony Xperia XA1 Plus Dual", "XA1_Plus_Dual.jpeg",
                "Android, экран 5.5\" IPS (1080x1920), Mediatek MT6757 Helio P20, ОЗУ 4 ГБ, " +
                "флэш-память 32 ГБ, карты памяти, камера 23 Мп, аккумулятор 3430 мАч, 2 SIM", phone, 535, 0));
        productList.add(new Product("Sony Xperia L1 Dual", "L1_Dual.jpeg",
                "Android, экран 5.5\" IPS (720x1280), Mediatek MT6737T, ОЗУ 2 ГБ, " +
                "флэш-память 16 ГБ, карты памяти, камера 13 Мп, аккумулятор 2620 мАч, 2 SIM", phone, 294, 0));
        productList.add(new Product("Sony Walkman NW-WS623", "NW-WS623.jpeg",
                "BT, время работы 12 часов", mp3Player, 382, 0));
        productList.add(new Product("Sony NW-WS413", "NW-WS413.jpg",
                "время работы 12 часов", mp3Player, 359, 0));
        productList.add(new Product("Sony NWZ-B183F", "NWZ-B183F.jpeg",
                "экран 0.9\" OLED 128 x 36, радио, время работы 20 часов", mp3Player, 175, 0));
        productList.add(new Product("Sony WH-1000XM4", "WH-1000XM4.jpeg",
                "беспроводные наушники с микрофоном, мониторные (охватывающие), " +
                "портативные, Bluetooth 5.0, NFC, 4-40000 Гц, поворотные чашки/складное оголовье, " +
                "быстрая зарядка, время работы 38 ч, кабель 1.2 м", headphones, 845, 0));
        productList.add(new Product("Sony WF-XB700", "WF-XB700.jpeg",
                "беспроводные наушники с микрофоном, внутриканальные, портативные, " +
                "Bluetooth 5.0, 20-20000 Гц, быстрая зарядка, время работы 9 ч", headphones, 209, 0));
        productList.add(new Product("Sony WF-1000XM3", "WF-1000XM3.jpeg",
                "беспроводные наушники с микрофоном, внутриканальные, портативные, " +
                "Bluetooth 5.0, NFC, 20-20000 Гц, время работы 8 ч", headphones, 442, 0));
        productList.add(new Product("Sony WI-C200", "WI-C200.jpeg",
                "беспроводные наушники с микрофоном, внутриканальные, портативные, " +
                "Bluetooth 5.0, 20-20000 Гц, магниты, быстрая зарядка, время работы 15 ч", headphones, 63, 0));
        productList.add(new Product("Sony PS5 Pulse 3D", "PS5 Pulse 3D.jpeg",
                "беспроводные наушники с микрофоном, мониторные (охватывающие), " +
                "геймерские, время работы 12 ч", headphones, 315, 0));
        productList.add(new Product("Sony WI-XB400", "WI-XB400.jpeg",
                "беспроводные наушники с микрофоном, внутриканальные, портативные, " +
                "Bluetooth 5.0, 20-20000 Гц, магниты, быстрая зарядка, время работы 15 ч", headphones, 95, 0));
        productList.add(new Product("Sony WH-CH510", "WH-CH510.jpeg",
                "беспроводные наушники с микрофоном, накладные, портативные, " +
                "Bluetooth 5.0, 20-20000 Гц, поворотные чашки, быстрая зарядка, время работы 35 ч", headphones, 89, 0));
        productList.add(new Product("Sony WI-SP510", "WI-SP510.jpeg",
                "беспроводные наушники с микрофоном, внутриканальные, портативные/спортивные, " +
                "Bluetooth 5.0, 20-20000 Гц, магниты, быстрая зарядка, время работы 15 ч", headphones, 148, 0));
        productList.add(new Product("Sony WH-CH710N", "WH-CH710N.jpeg",
                "беспроводные наушники с микрофоном, мониторные (охватывающие), " +
                "портативные, Bluetooth 5.0, NFC, 7-20000 Гц, поворотные чашки, быстрая зарядка, " +
                "время работы 35 ч, кабель 1.2 м", headphones, 255, 0));
        productList.add(new Product("Sony Gold Wireless", "Gold Wireless.jpeg",
                "беспроводные наушники с микрофоном, мониторные (охватывающие), " +
                "геймерские, время работы 7 ч, кабель 1.2 м", headphones, 230, 0));
        productList.add(new Product("Sony MDR-EX155AP", "MDR-EX155AP.jpeg",
                "наушники с микрофоном, внутриканальные, портативные, " +
                "5-24000 Гц, кабель 1.2 м", headphones, 40, 0));
        productList.add(new Product("Sony MDR-EX255AP", "MDR-EX255AP.jpeg",
                "наушники с микрофоном, внутриканальные, портативные, " +
                "5-25000 Гц, кабель 1.2 м", headphones, 57, 0));
        productList.add(new Product("Sony KD-55XH9505", "KD-55XH9505.jpeg",
                "54.6\" 3840x2160 (4K UHD), матрица VA, частота матрицы 120 Гц, " +
                "Smart TV (Android TV), HDR, Wi-Fi", TV, 3300, 0));
        productList.add(new Product("Sony KD-55XH9096", "KD-55XH9096.jpeg",
                "54.6\" 3840x2160 (4K UHD), матрица VA, частота матрицы 120 Гц, " +
                "Smart TV (Android TV), HDR, Wi-Fi", TV, 3090, 0));
        productList.add(new Product("Sony KD-65A8", "KD-65A8.jpeg",
                "65\" 3840x2160 (4K UHD), матрица OLED, частота матрицы 120 Гц, " +
                "Smart TV (Android TV), HDR, Wi-Fi", TV, 5999, 0));
        productList.add(new Product("Sony KD-43XH8005", "KD-43XH8005.jpeg",
                "42.5\" 3840x2160 (4K UHD), частота матрицы 60 Гц, Smart TV (Android TV), " +
                "HDR, Wi-Fi", TV, 1800, 0));
        productList.add(new Product("Sony KD-49XH8596", "KD-49XH8596.jpeg",
                "48.5\" 3840x2160 (4K UHD), частота матрицы 120 Гц, Smart TV (Android TV), " +
                "HDR, Wi-Fi", TV, 2568, 0));
        productList.add(new Product("Sony KD-43XH8096", "KD-43XH8096.jpeg",
                "48.5\" 3840x2160 (4K UHD), частота матрицы 60 Гц, Smart TV (Android TV), " +
                "HDR, Wi-Fi", TV, 1900, 0));
        productList.add(new Product("Sony KD-55XG9505", "KD-55XG9505.jpeg",
                "54.6\" 3840x2160 (4K UHD), матрица VA, частота матрицы 120 Гц, " +
                "Smart TV (Android TV), HDR, Wi-Fi", TV, 2750, 0));
        productList.add(new Product("Sony KD-75ZH8", "KD-75ZH8.jpeg",
                "75\" 7680x4320 (8K UHD), частота матрицы 120 Гц, Smart TV (Android TV), " +
                "HDR, Wi-Fi", TV, 14500, 0));
        productList.add(new Product("Sony KD-43XG8096", "KD-43XG8096.jpeg",
                "42.5\" 3840x2160 (4K UHD), частота матрицы 60 Гц, Smart TV (Android TV), " +
                "HDR, Wi-Fi", TV, 1830, 0));
        productList.add(new Product("Sony KD-85ZG9", "KD-85ZG9.jpeg",
                "84.6\" 7680x4320 (8K UHD), матрица VA, частота матрицы 120 Гц, " +
                "Smart TV (Android TV), HDR, Wi-Fi", TV, 25499, 0));
        productList.add(new Product("Sony KD-65AG9", "KD-65AG9.jpeg",
                "65\" 3840x2160 (4K UHD), матрица OLED, частота матрицы 120 Гц, " +
                "Smart TV (Android TV), HDR, Wi-Fi", TV, 6999, 0));
        productList.add(new Product("Sony KD-55XF9005", "KD-55XF9005.jpeg",
                "54.6\" 3840x2160 (4K UHD), матрица VA, частота матрицы 120 Гц, " +
                "Smart TV (Android TV), HDR, Wi-Fi", TV, 2599, 0));
        productList.add(new Product("Sony Alpha a7 III Body", "Alpha a7 III Body.jpeg",
                "беззеркальная камера, матрица Full frame (полный кадр) 24.2 Мп, " +
                "без объектива (body), Wi-Fi", photo, 4700, 0));
        productList.add(new Product("Sony Alpha a6000 Kit 16-50mm", "Alpha a6000 Kit.jpg",
                "беззеркальная камера, матрица APS-C (1.5 crop) 24.3 Мп, с объективом " +
                "F3.5-5.6 16-50 мм, Wi-Fi", photo, 1619, 0));
        productList.add(new Product("Sony Alpha a6600 Body", "Alpha a6600 Body.jpeg",
                "беззеркальная камера, матрица APS-C (1.5 crop) 24.2 Мп, без объектива " +
                "(body), Wi-Fi", photo, 3598, 0));
        productList.add(new Product("Sony Alpha a6400 Body", "Alpha a6400 Body.jpeg",
                "беззеркальная камера, матрица APS-C (1.5 crop) 24.2 Мп, без объектива " +
                "(body), Wi-Fi", photo, 2580, 0));
        productList.add(new Product("Sony Alpha a7C Body", "Alpha a7C Body.jpeg",
                "беззеркальная камера, матрица Full frame (полный кадр) 24.2 Мп, " +
                "без объектива (body), Wi-Fi", photo, 5280, 0));
        productList.add(new Product("Sony Cyber-shot DSC-RX100", "Cyber-shot DSC-RX100.jpg",
                "компакт-камера, матрица 1\" 20.2 Мп, объектив 3.6X F1.8-4.9 28-100 мм",
                photo, 1099, 0));
        productList.add(new Product("Sony SDXC SF-E128 128GB", "SDXC SF-E128.jpeg",
                "128 ГБ, класс 10, UHS-II (класс U3), V60, чтение: 270 МБ/с, " +
                "запись: 120 МБ/с", memoryCard, 220, 0));
        productList.add(new Product("Sony XQD G Series 64GB", "XQD G Series.jpeg",
                "64 ГБ, чтение: 440 МБ/с, запись: 400 МБ/с", memoryCard, 570, 0));
        productList.add(new Product("Sony SF-M Tough SDXC 256GB", "SF-M Tough SDXC.jpeg",
                "256 Гб, класс 10, UHS-II (класс U3), V60, чтение: 277 МБ/с, " +
                "запись: 150 МБ/с", memoryCard, 757, 0));
        productList.add(new Product("Sony SDXC SF-M Series UHS-II 128GB",
                "SDXC SF-M Series UHS-II.jpeg",
                "128 ГБ, класс 10, UHS-II (класс U3), чтение: 260 МБ/с, запись: 100 МБ/с",
                memoryCard, 237, 0));
        productList.add(new Product("Sony Memory Stick PRO Duo MSX-M8GST 8GB",
                "PRO Duo MSX-M8GST.jpg", "8 ГБ, запись: 4 МБ/с", memoryCard, 10, 0));
    }

    public static List<Category> getCategoriesList() {
        return categoriesList;
    }

    public static List<Product> getProductList() {
        return productList;
    }

    public static List<User> getUserList() {
        return userList;
    }

    public static void setUser(User user) {
        userList.add(user);
    }

    public static Product getProduct(String productName) throws NoFoundException {
        for (Product product : productList) {
            if (product.getName().equals(productName)) return product;
        }
        throw new NoFoundException("Product: " + productName + " not found.");
    }
}
