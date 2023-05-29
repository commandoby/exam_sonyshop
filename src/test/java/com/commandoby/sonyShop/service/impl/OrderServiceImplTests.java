package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.components.Category;
import com.commandoby.sonyShop.components.Image;
import com.commandoby.sonyShop.components.Order;
import com.commandoby.sonyShop.components.Product;
import com.commandoby.sonyShop.components.User;
import com.commandoby.sonyShop.exceptions.NotFoundException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.repository.OrderRepository;
import com.commandoby.sonyShop.service.OrderService;
import com.commandoby.sonyShop.service.ProductService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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
    }

    @BeforeEach
    public void blank() throws ServiceException {
        Category category = new Category("Phone", "phone", new Image(), 1);

        product1 = Product.newBuilder().withName("Sony Xperia 10 II")
                .withCategory(category).withPrice(1000).withQuantity(5).build();
        product1.setId(1);
        product2 = Product.newBuilder().withName("Sony Xperia 1 II")
                .withCategory(category).withPrice(2500).withQuantity(1).build();
        product2.setId(2);

        order = new Order();

        user = User.newBuilder().withName("User").withEmail("user").withBalance(10000).build();

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
        assertEquals("Will not find a product to remove: Sony Xperia 10 II", exception.getMessage());
    }

    @Test
    public void orderPayMethod_DataTest() throws ServiceException {
        orderService.orderPayMethod(user, order);
        assertEquals(LocalDate.now(), order.getDate());
    }

    @Test
    public void orderPayMethod_DataNotNullTest() throws ServiceException {
        orderService.orderPayMethod(user, order);
        assertNotNull(order.getDate());
    }

    @Test
    public void orderPayMethod_SetUserTest() throws ServiceException {
        orderService.orderPayMethod(user, order);
        assertEquals(user, order.getUser());
    }

    @Test
    public void orderPayMethod_UpdateQuantityProductsTest() throws ServiceException {
        orderService.addProductToBasket(order, product1);
        orderService.orderPayMethod(user, order);
        assertEquals(3, product1.getQuantity());
    }

    @Test
    public void orderPayMethod_NotEnoughProductsTest() throws ServiceException {
        orderService.addProductToBasket(order, product2);
        orderService.orderPayMethod(user, order);
        assertEquals(1, order.getProductList().size());
    }

    @Test
    public void orderPayMethod_NotEnoughBalanceExceptionTest() throws ServiceException {
        user.setBalance(500);
        Throwable exception = assertThrows(ServiceException.class,
                () -> orderService.orderPayMethod(user, order));
        assertEquals("User has insufficient funds: user", exception.getMessage());
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("All OrderServiceImpl tests are finished!");
    }
}
