package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.components.Product;
import com.commandoby.sonyShop.exceptions.NotFoundException;
import com.commandoby.sonyShop.repository.OrderRepository;
import com.commandoby.sonyShop.components.Order;
import com.commandoby.sonyShop.components.User;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.OrderService;
import com.commandoby.sonyShop.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final Logger log = LogManager.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    @Override
    public int create(Order order) throws ServiceException {
        orderRepository.save(order);
        return order.getId();
    }

    @Override
    public Order read(int id) throws ServiceException {
        return orderRepository.findById(id).orElseThrow(() ->
                new ServiceException("Error retrieving a order from the database by ID: "
                        + id + ".", new Exception()));
    }

    @Override
    public void update(Order order) throws ServiceException {
        orderRepository.save(order);
    }

    @Override
    public void delete(Order order) throws ServiceException {
        orderRepository.delete(order);
    }

    @Override
    public List<Order> readOrdersByUser(User user) throws ServiceException {
        return orderRepository.findAllByUser(user);
    }


    @Override
    public void addProductToBasket(Order order, Product product) throws ServiceException {
        if (order == null) order = new Order();
        order.getProductList().add(product);
        updateOrderPrice(order);
    }

    @Override
    public void removeProductWithOfBasketByNumber(Order order, int number) throws NotFoundException, ServiceException {
        if (order.getProductList().get(number) != null) {
            order.getProductList().remove(number);
            updateOrderPrice(order);
            return;
        }
        throw new NotFoundException("Will not find a product to remove by number: " + number);
    }

    @Override
    public void removeProductWithOfBasket(Order order, Product product) throws NotFoundException, ServiceException {
        for (int i = 0; i < order.getProductList().size(); i++) {
            if (order.getProductList().get(i).getId() == product.getId()) {
                order.getProductList().remove(i);
                updateOrderPrice(order);
                return;
            }
        }
        throw new NotFoundException("Will not find a product to remove: " + product.getName());
    }

    private void updateOrderPrice(Order order) {
        order.setOrderPrice(order
                .getProductList()
                .stream()
                .mapToInt(Product::getPrice)
                .sum());
    }

    @Override
    public void orderPayMethod(User user, Order order) throws ServiceException {
        if (user.getBalance() < order.getOrderPrice()) {
            throw new ServiceException("User has insufficient funds: "
                    + user.getEmail(), new Exception());
        }

        updateProductQuantity(order);
        if (order.getProductList().size() != 0) {
            order.setDate(LocalDate.now());
            order.setUser(user);
            create(order);
        }
    }

    private void updateProductQuantity(Order order) throws ServiceException {
        Map<Integer, List<Product>> groupedProducts = order.getProductList()
                .stream().collect(Collectors.groupingBy(Product::getId));

        for (Map.Entry<Integer, List<Product>> entry : groupedProducts.entrySet()) {
            updateQuantityProducts(order, entry.getValue());
        }
    }

    private void updateQuantityProducts(Order order, List<Product> products) throws ServiceException {
        Product product = products.get(0);
        if (product.getQuantity() >= products.size()) {
            try {
                product.setQuantity(product.getQuantity() - products.size());
                productService.update(product);
            } catch (ServiceException e) {
                throw new ServiceException("Duplicate product update error during purchase from ID: "
                        + product.getId() + ".", e);
            }
        } else {
            try {
                log.info("Trying to purchase " + products.size()
                        + " products. In stock: " + product.getQuantity() + ".");

                for (int i = 0; i < products.size(); i++) {
                    removeProductWithOfBasket(order, product);
                }
            } catch (NotFoundException | ServiceException e) {
                throw new ServiceException("Error removing duplicate product from cart from ID: "
                        + product.getId() + ".", e);
            }
        }
    }
}
