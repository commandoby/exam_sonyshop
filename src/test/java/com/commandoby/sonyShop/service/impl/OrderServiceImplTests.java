package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.components.Category;
import com.commandoby.sonyShop.components.Order;
import com.commandoby.sonyShop.components.Product;
import com.commandoby.sonyShop.components.User;
import com.commandoby.sonyShop.exceptions.NotFoundException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.repository.OrderRepository;
import com.commandoby.sonyShop.service.OrderService;
import com.commandoby.sonyShop.service.ProductService;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class OrderServiceImplTests {
    private static OrderRepository orderRepositoryMock;
    private static ProductService productServiceMock;

    private static OrderService orderService;

    private static Order order;
    private static User user;
    private static Product product1;
    private static Product product2;

    @BeforeAll
    public static void setUp() {
        orderRepositoryMock = Mockito.mock(OrderRepository.class);
        productServiceMock = Mockito.mock(ProductService.class);

        orderService = new OrderServiceImpl(orderRepositoryMock, productServiceMock);

        user = User.newBuilder().withName("User").withEmail("user").withBalance(10000).build();

        Category category = new Category("Phone", "phone", "phone.jpeg", 1);

        product1 = new Product("Sony Xperia 10 II XQ-AU52 Dual", "10_II_XQ-AU52_Dual.jpeg",
                "Android, экран 6\" OLED (1080x2520), Qualcomm Snapdragon 665, ОЗУ 4 ГБ, флэш-память 128 ГБ, " +
                        "карты памяти, камера 12 Мп, аккумулятор 3600 мАч, 2 SIM", category, 1000, 5);
        product2 = new Product("Sony Xperia 1 II XQ-AT52", "1_II_XQ-AT52.jpeg",
                "Android, экран 6.5\" OLED (1644x3840), Qualcomm Snapdragon 865, ОЗУ 12 ГБ, флэш-память 256 ГБ, " +
                        "карты памяти, камера 12 Мп, аккумулятор 4000 мАч, 2 SIM", category,2500, 1);
        product1.setId(1);
        product2.setId(2);
    }

    @BeforeEach
    public void blank() throws ServiceException {
        order = new Order();
        orderService.addProductToBasket(order, product1);
        orderService.addProductToBasket(order, product2);
    }

    @Test
    public void addProductToBasket_ListSizeTest() {
        assertEquals(2, order.getProductList().size());
    }

    @Test
    public void addProductToBasket_PriceTest() throws ServiceException {
        orderService.addProductToBasket(order, product2);
        assertEquals(6000, order.getOrderPrice());
    }

    @Test
    public void removeProductWithOfBasketByNumber_Test() throws ServiceException, NotFoundException {
        orderService.removeProductWithOfBasketByNumber(order, 0);
        assertEquals(product2, order.getProductList().get(0));
    }

    @Test
    public void removeProductWithOfBasketByNumber_PriceTest() throws ServiceException, NotFoundException {
        orderService.removeProductWithOfBasketByNumber(order, 1);
        assertEquals(1000, order.getOrderPrice());
    }

    @Test
    public void removeProductWithOfBasket_Test() throws ServiceException, NotFoundException {
        orderService.removeProductWithOfBasket(order, product1);
        assertEquals(product2, order.getProductList().get(0));
    }

    @Test
    public void removeProductWithOfBasket_PriceTest() throws ServiceException, NotFoundException {
        orderService.removeProductWithOfBasket(order, product1);
        assertEquals(2500, order.getOrderPrice());
    }

    @Test
    public void removeProductWithOfBasket_ExceptionTest() throws ServiceException, NotFoundException {
        orderService.removeProductWithOfBasket(order, product1);
        Throwable exception = assertThrows(NotFoundException.class,
                () -> orderService.removeProductWithOfBasket(order, product1));
        assertEquals("Will not find a product to remove: " + product1.getName(), exception.getMessage());
    }

    @AfterAll
    public static void tearDown(){
        System.out.println("All tests are finished!");
    }
}
