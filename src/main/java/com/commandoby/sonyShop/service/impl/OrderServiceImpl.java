package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.components.Product;
import com.commandoby.sonyShop.exceptions.NotFoundException;
import com.commandoby.sonyShop.repository.OrderRepository;
import com.commandoby.sonyShop.components.Order;
import com.commandoby.sonyShop.components.User;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.OrderService;
import com.commandoby.sonyShop.service.ProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

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
                new ServiceException("Error retrieving a order from the database by ID: " + id + ".", new Exception())
        );
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
    public Product addProductToBasket(Order order, int product_id) throws ServiceException {
        Product product = productService.read(product_id);

        if (order == null) order = new Order();
        order.getProductList().add(product);
        updateOrderPrice(order);

        return product;
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
    public void removeProductWithOfBasketById(Order order, int id) throws NotFoundException, ServiceException {
        for (int i = 0; i < order.getProductList().size(); i++) {
            if (order.getProductList().get(i).getId() == id) {
                order.getProductList().remove(i);
                updateOrderPrice(order);
                return;
            }
        }
        throw new NotFoundException("Will not find a product to remove by id: " + id);
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
        try {
            updateProductQuantity(order);
            if (order.getProductList().size() != 0) {
                order.setDate(LocalDate.now());
                order.setUser(user);
                create(order);
            }
        } catch (ServiceException e) {
            throw new ServiceException("Order update error during purchase.", e);
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
                removeProductWithOfBasketById(order, product.getId());
            } catch (NotFoundException | ServiceException e) {
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
                    removeProductWithOfBasketById(order, product.getId());
                }
            } catch (NotFoundException | ServiceException e) {
                throw new ServiceException("Error removing duplicate product from cart from id: "
                        + product.getId() + ".", e);
            }
        }
    }
}
