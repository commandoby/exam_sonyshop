package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.repository.domain.Order;
import com.commandoby.sonyShop.repository.domain.Product;
import com.commandoby.sonyShop.repository.domain.User;
import com.commandoby.sonyShop.service.OrderService;
import com.commandoby.sonyShop.service.ProductService;
import com.commandoby.sonyShop.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PayMethodsImpl {

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
            throw new ServiceException("Order update error during purchase.", e);
        }
    }

    public void userPayMethod(User user, Order order) throws ServiceException {
        try {
            user.setBalance(user.getBalance() - order.getOrderPrice());
            user.addOrder(order);
            userService.update(user);
        } catch (ServiceException e) {
            throw new ServiceException("User update failed during purchase.", e);
        }
    }

    private void updateProductQuantity(Order order) throws ServiceException {
        List<Product> products = order.getProductList();
        products.sort(Comparator.comparingInt(Product::getId));
        List<List<Product>> doubleProducts = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (i + 1 < products.size() && products.get(i).getId() == products.get(i + 1).getId()) {
                List<Product> doubleProduct = new ArrayList<>();
                while (i + 1 < products.size() && products.get(i).getId() == products.get(i + 1).getId()) {
                    doubleProduct.add(products.get(i));
                    i++;
                }
                doubleProduct.add(products.get(i));
                doubleProducts.add(doubleProduct);
            } else {
                Product product = products.get(i);
                updateQuantityOneProduct(order, product);
            }
        }

        if (doubleProducts.size() != 0) {
            for (List<Product> doubleProduct : doubleProducts) {
                updateQuantitySomeProducts(order, doubleProduct);
            }
        }
    }

    private void updateQuantityOneProduct(Order order, Product product) throws ServiceException {
        if (product.getQuantity() > 0) {
            try {
                product.setQuantity(product.getQuantity() - 1);
                productService.update(product);
            } catch (ServiceException e) {
                throw new ServiceException("Product update error during purchase from ID: "
                        + product.getId() + ".", e);
            }
        } else {
            try {
                useBasket.removeProductWithOfBasketById(order, product.getId());
            } catch (NoFoundException | ServiceException e) {
                throw new ServiceException("Error deleting a product from the cart from the ID: "
                        + product.getId() + ".", e);
            }
        }
    }

    private void updateQuantitySomeProducts(Order order, List<Product> doubleProduct) throws ServiceException {
        Product product = doubleProduct.get(0);
        if (product.getQuantity() >= doubleProduct.size()) {
            try {
                product.setQuantity(product.getQuantity() - doubleProduct.size());
                productService.update(product);
            } catch (ServiceException e) {
                throw new ServiceException("Duplicate product update error during purchase from ID: "
                        + product.getId() + ".", e);
            }
        } else {
            try {
                for (int i = 0; i < doubleProduct.size(); i++) {
                    useBasket.removeProductWithOfBasketById(order, product.getId());
                }
            } catch (NoFoundException | ServiceException e) {
                throw new ServiceException("Error removing duplicate product from cart from id: "
                        + product.getId() + ".", e);
            }
        }
    }
}
