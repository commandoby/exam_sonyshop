package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.repository.domain.Order;
import com.commandoby.sonyShop.repository.domain.Product;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class UseBasketImpl {

    private final ProductService productService;

    public UseBasketImpl(ProductService productService) {
        this.productService = productService;
    }

    public Product addProductToBasket(Order order, int product_id) throws ServiceException {
        Product product = productService.read(product_id);

        if (order == null) order = new Order();
        order.getProductList().add(product);
        updateOrderPrice(order);

        return product;
    }

    public void removeProductWithOfBasket(Order order, int id) throws NoFoundException, ServiceException {
        if (order.getProductList().get(id) != null) {
            order.getProductList().remove(id);
            updateOrderPrice(order);
        } else {
            throw new NoFoundException("Will not find a product to remove by id: " + id);
        }
    }

    private void updateOrderPrice(Order order) {
        order.setOrderPrice(order
                .getProductList()
                .stream()
                .mapToInt(productOrder -> productOrder.getPrice())
                .sum());
    }
}
