package com.commandoby.sonyShop.classies;

import com.commandoby.sonyShop.exceptions.NoFoundException;

import java.util.ArrayList;
import java.util.List;

public class ShopContent {
    private static List<User> userList = new ArrayList<>();
    private static final List<Category> categoriesList = new ArrayList<>();
    private static final List<Product> productList = new ArrayList<>();
    private static final Category phone = new Category("Phone", "phone.jpeg", "phone");
    private static final Category mp3Player = new Category("MP3 player", "player.jpeg", "player");
    private static final Category headphones = new Category("Headphones", "headphones.jpeg", "headphones");
    private static final Category TV = new Category("TV", "tv.jpeg", "tv");
    private static final Category photo = new Category("Photo", "photo.jpeg", "photo");
    private static final Category memoryCard = new Category("Memory card", "memcard.jpeg", "memcard");

    static {
        userList.add(new User("Admin", "Admin", "admin", "admin", "1980-01-01", 9999999));

        categoriesList.add(phone);
        categoriesList.add(mp3Player);
        categoriesList.add(headphones);
        categoriesList.add(TV);
        categoriesList.add(photo);
        categoriesList.add(memoryCard);

        productList.add(new Product("Sony Xperia 10 II XQ-AU52 Dual", "10_II_XQ-AU52_Dual.jpeg",
                phone, "Android, экран 6\" OLED (1080x2520), Qualcomm Snapdragon 665, ОЗУ 4 ГБ, " +
                "флэш-память 128 ГБ, карты памяти, камера 12 Мп, аккумулятор 3600 мАч, 2 SIM", 899));
        productList.add(new Product("Sony Xperia 1 II XQ-AT52", "1_II_XQ-AT52.jpeg",
                phone, "Android, экран 6.5\" OLED (1644x3840), Qualcomm Snapdragon 865, ОЗУ 12 ГБ, " +
                "флэш-память 256 ГБ, карты памяти, камера 12 Мп, аккумулятор 4000 мАч, 2 SIM", 2679));
        productList.add(new Product("Sony Xperia 5 II Dual", "5_II_Dual.jpeg",
                phone, "Android, экран 6.1\" OLED (1080x2520), Qualcomm Snapdragon 865, ОЗУ 8 ГБ, " +
                "флэш-память 128 ГБ, карты памяти, камера 12 Мп, аккумулятор 4000 мАч, 2 SIM", 2250));
        productList.add(new Product("Sony Xperia 1", "1.jpeg",
                phone, "Android, экран 6.5\" OLED (1644x3840), Qualcomm Snapdragon 855, ОЗУ 6 ГБ, " +
                "флэш-память 64 ГБ, карты памяти, камера 12 Мп, аккумулятор 3330 мАч, 1 SIM", 1450));
        productList.add(new Product("Sony Xperia 5 J9210", "5_J9210.jpeg",
                phone, "Android, экран 6.1\" OLED (1080x2520), Qualcomm Snapdragon 855, ОЗУ 6 ГБ, " +
                "флэш-память 128 ГБ, карты памяти, камера 12 Мп, аккумулятор 3140 мАч, 2 SIM", 1699));
        productList.add(new Product("Sony Xperia 10 I3113 Dual", "10_I3113_Dual.jpeg",
                phone, "Android, экран 6\" IPS (1080x2520), Qualcomm Snapdragon 630, ОЗУ 3 ГБ, " +
                "флэш-память 64 ГБ, карты памяти, камера 13 Мп, аккумулятор 2870 мАч, 2 SIM", 699));
        productList.add(new Product("Sony Xperia L3 I4312 Dual", "L3_I4312_Dual.jpeg",
                phone, "Android, экран 5.7\" IPS (720x1440), Mediatek MT6762 Helio P22, ОЗУ 3 ГБ, " +
                "флэш-память 32 ГБ, карты памяти, камера 13 Мп, аккумулятор 3300 мАч, 2 SIM", 499));
        productList.add(new Product("Sony Xperia 10 Plus I4293 Dual", "10_Plus_I4293_Dual.jpeg",
                phone, "Android, экран 6.5\" IPS (1080x2520), Qualcomm Snapdragon 636, ОЗУ 6 ГБ, " +
                "флэш-память 64 ГБ, карты памяти, камера 12 Мп, аккумулятор 3000 мАч, 2 SIM", 939));
        productList.add(new Product("Sony Xperia XZ2 Dual", "XZ2_Dual.jpeg",
                phone, "Android, экран 5.7\" IPS (1080x2160), Qualcomm Snapdragon 845, ОЗУ 4 ГБ, " +
                "флэш-память 64 ГБ, карты памяти, камера 19 Мп, аккумулятор 3180 мАч, 2 SIM", 1100));
        productList.add(new Product("Sony Xperia XA1 Plus Dual", "XA1_Plus_Dual.jpeg",
                phone, "Android, экран 5.5\" IPS (1080x1920), Mediatek MT6757 Helio P20, ОЗУ 4 ГБ, " +
                "флэш-память 32 ГБ, карты памяти, камера 23 Мп, аккумулятор 3430 мАч, 2 SIM", 535));
        productList.add(new Product("Sony Xperia L1 Dual", "L1_Dual.jpeg",
                phone, "Android, экран 5.5\" IPS (720x1280), Mediatek MT6737T, ОЗУ 2 ГБ, " +
                "флэш-память 16 ГБ, карты памяти, камера 13 Мп, аккумулятор 2620 мАч, 2 SIM", 294));
        productList.add(new Product("Sony Walkman NW-WS623", "NW-WS623.jpeg",
                mp3Player, "BT, время работы 12 часов", 382));
        productList.add(new Product("Sony NW-WS413", "NW-WS413.jpg",
                mp3Player, "время работы 12 часов", 359));
        productList.add(new Product("Sony NWZ-B183F", "NWZ-B183F.jpeg",
                mp3Player, "экран 0.9\" OLED 128 x 36, радио, время работы 20 часов", 175));
        productList.add(new Product("Sony WH-1000XM4", "WH-1000XM4.jpeg",
                headphones, "беспроводные наушники с микрофоном, мониторные (охватывающие), " +
                "портативные, Bluetooth 5.0, NFC, 4-40000 Гц, поворотные чашки/складное оголовье, " +
                "быстрая зарядка, время работы 38 ч, кабель 1.2 м", 845));
        productList.add(new Product("Sony WF-XB700", "WF-XB700.jpeg",
                headphones, "беспроводные наушники с микрофоном, внутриканальные, портативные, " +
                "Bluetooth 5.0, 20-20000 Гц, быстрая зарядка, время работы 9 ч", 209));
        productList.add(new Product("Sony WF-1000XM3", "WF-1000XM3.jpeg",
                headphones, "беспроводные наушники с микрофоном, внутриканальные, портативные, " +
                "Bluetooth 5.0, NFC, 20-20000 Гц, время работы 8 ч", 442));
        productList.add(new Product("Sony WI-C200", "WI-C200.jpeg",
                headphones, "беспроводные наушники с микрофоном, внутриканальные, портативные, " +
                "Bluetooth 5.0, 20-20000 Гц, магниты, быстрая зарядка, время работы 15 ч", 63));
        productList.add(new Product("Sony PS5 Pulse 3D", "PS5 Pulse 3D.jpeg",
                headphones, "беспроводные наушники с микрофоном, мониторные (охватывающие), " +
                "геймерские, время работы 12 ч", 315));
        productList.add(new Product("Sony WI-XB400", "WI-XB400.jpeg",
                headphones, "беспроводные наушники с микрофоном, внутриканальные, портативные, " +
                "Bluetooth 5.0, 20-20000 Гц, магниты, быстрая зарядка, время работы 15 ч", 95));
        productList.add(new Product("Sony WH-CH510", "WH-CH510.jpeg",
                headphones, "беспроводные наушники с микрофоном, накладные, портативные, " +
                "Bluetooth 5.0, 20-20000 Гц, поворотные чашки, быстрая зарядка, время работы 35 ч", 89));
        productList.add(new Product("Sony WI-SP510", "WI-SP510.jpeg",
                headphones, "беспроводные наушники с микрофоном, внутриканальные, портативные/спортивные, " +
                "Bluetooth 5.0, 20-20000 Гц, магниты, быстрая зарядка, время работы 15 ч", 148));
        productList.add(new Product("Sony WH-CH710N", "WH-CH710N.jpeg",
                headphones, "беспроводные наушники с микрофоном, мониторные (охватывающие), " +
                "портативные, Bluetooth 5.0, NFC, 7-20000 Гц, поворотные чашки, быстрая зарядка, " +
                "время работы 35 ч, кабель 1.2 м", 255));
        productList.add(new Product("Sony Gold Wireless", "Gold Wireless.jpeg",
                headphones, "беспроводные наушники с микрофоном, мониторные (охватывающие), " +
                "геймерские, время работы 7 ч, кабель 1.2 м", 230));
        productList.add(new Product("Sony MDR-EX155AP", "MDR-EX155AP.jpeg",
                headphones, "наушники с микрофоном, внутриканальные, портативные, " +
                "5-24000 Гц, кабель 1.2 м", 40));
        productList.add(new Product("Sony MDR-EX255AP", "MDR-EX255AP.jpeg",
                headphones, "наушники с микрофоном, внутриканальные, портативные, " +
                "5-25000 Гц, кабель 1.2 м", 57));
        productList.add(new Product("Sony KD-55XH9505", "KD-55XH9505.jpeg",
                TV, "54.6\" 3840x2160 (4K UHD), матрица VA, частота матрицы 120 Гц, " +
                "Smart TV (Android TV), HDR, Wi-Fi", 3300));
        productList.add(new Product("Sony KD-55XH9096", "KD-55XH9096.jpeg",
                TV, "54.6\" 3840x2160 (4K UHD), матрица VA, частота матрицы 120 Гц, " +
                "Smart TV (Android TV), HDR, Wi-Fi", 3090));
        productList.add(new Product("Sony KD-65A8", "KD-65A8.jpeg",
                TV, "65\" 3840x2160 (4K UHD), матрица OLED, частота матрицы 120 Гц, " +
                "Smart TV (Android TV), HDR, Wi-Fi", 5999));
        productList.add(new Product("Sony KD-43XH8005", "KD-43XH8005.jpeg",
                TV, "42.5\" 3840x2160 (4K UHD), частота матрицы 60 Гц, Smart TV (Android TV), " +
                "HDR, Wi-Fi", 1800));
        productList.add(new Product("Sony KD-49XH8596", "KD-49XH8596.jpeg",
                TV, "48.5\" 3840x2160 (4K UHD), частота матрицы 120 Гц, Smart TV (Android TV), " +
                "HDR, Wi-Fi", 2568));
        productList.add(new Product("Sony KD-43XH8096", "KD-43XH8096.jpeg",
                TV, "48.5\" 3840x2160 (4K UHD), частота матрицы 60 Гц, Smart TV (Android TV), " +
                "HDR, Wi-Fi", 1900));
        productList.add(new Product("Sony KD-55XG9505", "KD-55XG9505.jpeg",
                TV, "54.6\" 3840x2160 (4K UHD), матрица VA, частота матрицы 120 Гц, " +
                "Smart TV (Android TV), HDR, Wi-Fi", 2750));
        productList.add(new Product("Sony KD-75ZH8", "KD-75ZH8.jpeg",
                TV, "75\" 7680x4320 (8K UHD), частота матрицы 120 Гц, Smart TV (Android TV), " +
                "HDR, Wi-Fi", 14500));
        productList.add(new Product("Sony KD-43XG8096", "KD-43XG8096.jpeg",
                TV, "42.5\" 3840x2160 (4K UHD), частота матрицы 60 Гц, Smart TV (Android TV), " +
                "HDR, Wi-Fi", 1830));
        productList.add(new Product("Sony KD-85ZG9", "KD-85ZG9.jpeg",
                TV, "84.6\" 7680x4320 (8K UHD), матрица VA, частота матрицы 120 Гц, " +
                "Smart TV (Android TV), HDR, Wi-Fi", 25499));
        productList.add(new Product("Sony KD-65AG9", "KD-65AG9.jpeg",
                TV, "65\" 3840x2160 (4K UHD), матрица OLED, частота матрицы 120 Гц, " +
                "Smart TV (Android TV), HDR, Wi-Fi", 6999));
        productList.add(new Product("Sony KD-55XF9005", "KD-55XF9005.jpeg",
                TV, "54.6\" 3840x2160 (4K UHD), матрица VA, частота матрицы 120 Гц, " +
                "Smart TV (Android TV), HDR, Wi-Fi", 2599));
        productList.add(new Product("Sony Alpha a7 III Body", "Alpha a7 III Body.jpeg",
                photo, "беззеркальная камера, матрица Full frame (полный кадр) 24.2 Мп, " +
                "без объектива (body), Wi-Fi", 4700));
        productList.add(new Product("Sony Alpha a6000 Kit 16-50mm", "Alpha a6000 Kit.jpg",
                photo, "беззеркальная камера, матрица APS-C (1.5 crop) 24.3 Мп, с объективом " +
                "F3.5-5.6 16-50 мм, Wi-Fi", 1619));
        productList.add(new Product("Sony Alpha a6600 Body", "Alpha a6600 Body.jpeg",
                photo, "беззеркальная камера, матрица APS-C (1.5 crop) 24.2 Мп, без объектива " +
                "(body), Wi-Fi", 3598));
        productList.add(new Product("Sony Alpha a6400 Body", "Alpha a6400 Body.jpeg",
                photo, "беззеркальная камера, матрица APS-C (1.5 crop) 24.2 Мп, без объектива " +
                "(body), Wi-Fi", 2580));
        productList.add(new Product("Sony Alpha a7C Body", "Alpha a7C Body.jpeg",
                photo, "беззеркальная камера, матрица Full frame (полный кадр) 24.2 Мп, " +
                "без объектива (body), Wi-Fi", 5280));
        productList.add(new Product("Sony Cyber-shot DSC-RX100", "Cyber-shot DSC-RX100.jpg",
                photo, "компакт-камера, матрица 1\" 20.2 Мп, объектив 3.6X F1.8-4.9 28-100 мм",
                1099));
        productList.add(new Product("Sony SDXC SF-E128 128GB", "SDXC SF-E128.jpeg",
                memoryCard, "128 ГБ, класс 10, UHS-II (класс U3), V60, чтение: 270 МБ/с, " +
                "запись: 120 МБ/с", 220));
        productList.add(new Product("Sony XQD G Series 64GB", "XQD G Series.jpeg",
                memoryCard, "64 ГБ, чтение: 440 МБ/с, запись: 400 МБ/с", 570));
        productList.add(new Product("Sony SF-M Tough SDXC 256GB", "SF-M Tough SDXC.jpeg",
                memoryCard, "256 Гб, класс 10, UHS-II (класс U3), V60, чтение: 277 МБ/с, " +
                "запись: 150 МБ/с", 757));
        productList.add(new Product("Sony SDXC SF-M Series UHS-II 128GB",
                "SDXC SF-M Series UHS-II.jpeg", memoryCard,
                "128 ГБ, класс 10, UHS-II (класс U3), чтение: 260 МБ/с, запись: 100 МБ/с", 237));
        productList.add(new Product("Sony Memory Stick PRO Duo MSX-M8GST 8GB",
                "PRO Duo MSX-M8GST.jpg", memoryCard, "8 ГБ, запись: 4 МБ/с", 10));
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
