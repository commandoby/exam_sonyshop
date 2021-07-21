package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.NoFoundException;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.ProductService;
import com.commandoby.sonyShop.service.UseBasket;
import org.springframework.stereotype.Service;

@Service
public class UseBasketImpl implements UseBasket {

    private final ProductService productService;

    public UseBasketImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Product addProductToBasket(Order order, int product_id) throws ServiceException {
        Product product = productService.read(product_id);

        if (order == null) order = new Order();
        order.getProductList().add(product);
        updateOrderPrice(order);

        return product;
    }

    @Override
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
