package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.repository.domain.Order;
import com.commandoby.sonyShop.repository.domain.Product;
import com.commandoby.sonyShop.repository.domain.User;
import com.commandoby.sonyShop.service.OrderService;
import com.commandoby.sonyShop.service.ProductService;
import com.commandoby.sonyShop.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PayMethodsImpl {

    private final Logger log = Logger.getLogger(getClass());
    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;
    private final UseBasketImpl useBasket;

    public PayMethodsImpl(OrderService orderService, UserService userService,
                          ProductService productService, UseBasketImpl useBasket) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
        this.useBasket = useBasket;
    }

    public void orderPayMethod(User user, Order order) throws ServiceException {
        try {
            updateProductQuantity(order);
            if (order.getProductList().size() != 0) {
                order.setDate(LocalDate.now());
                order.setUser(user);
                orderService.create(order);
            }
        } catch (ServiceException e) {
            throw new ServiceException("", e);
        }
    }

    public void userPayMethod(User user, Order order) throws ServiceException {
        try {
            user.setBalance(user.getBalance() - order.getOrderPrice());
            user.addOrder(order);
            userService.update(user);
        } catch (ServiceException e) {
            throw new ServiceException("", e);
        }
    }

    private void updateProductQuantity(Order order) throws ServiceException {
        List<Product> products = order.getProductList();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.getQuantity() > 0) {
                try {
                    product.setQuantity(product.getQuantity() - 1);
                    productService.update(product);
                } catch (ServiceException e) {
                    throw new ServiceException("", e);
                }
            } else {
                try {
                    useBasket.removeProductWithOfBasket(order, i);
                } catch (NoFoundException | ServiceException e) {
                    throw new ServiceException("", e);
                }
            }
        }
    }
}
