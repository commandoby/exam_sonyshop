package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.components.Category;
import com.commandoby.sonyShop.components.Order;
import com.commandoby.sonyShop.components.Product;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.OrderService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTests {
    private static OrderService orderService;

    private static Order order;
    private static Category category;
    private static Product product;

    @BeforeAll
    public static void setUp() {
//        orderService = new OrderServiceImpl(new Ord)

        order = new Order();

        category = new Category("Phone", "phone", "phone.jpeg", 1);

        product = new Product("Sony Xperia 10 II XQ-AU52 Dual", "10_II_XQ-AU52_Dual.jpeg",
                "Android, экран 6\" OLED (1080x2520), Qualcomm Snapdragon 665, ОЗУ 4 ГБ, флэш-память 128 ГБ, " +
                        "карты памяти, камера 12 Мп, аккумулятор 3600 мАч, 2 SIM", category, 899, 24);
    }

    @Test
    public void addProductToBasketTestSizeList() throws ServiceException {
        orderService.addProductToBasket(order, product);
        Assertions.assertEquals(1, order.getProductList().size());
    }

    @AfterAll
    public static void tearDown(){
        System.out.println("All tests are finished!");
    }
}
