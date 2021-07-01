package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.dao.CategoryDao;
import com.commandoby.sonyShop.dao.OrderDao;
import com.commandoby.sonyShop.dao.ProductDao;
import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.dao.impl.CategoryDaoImpl;
import com.commandoby.sonyShop.dao.impl.OrderDaoImpl;
import com.commandoby.sonyShop.dao.impl.ProductDaoImpl;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public int create(Order order) throws ServiceException {
        order.setId(orderDao.create(order));
        if (!order.getProductList().isEmpty()) for (Product product : order.getProductList()) {
            orderDao.addProductByOrder(order.getId(), product.getId());
        }
        return order.getId();
    }

    @Override
    public Order read(int id) throws ServiceException {
        Order order = orderDao.read(id);
        readProducts(order);
        return order;
    }

    @Override
    public void update(Order order) throws ServiceException {
        orderDao.update(order);
    }

    @Override
    public void delete(Order order) throws ServiceException {

    }

    @Override
    public int createOrderByUser(Order order, int userId) throws ServiceException {
        order.setId(orderDao.createOrderByUser(order, userId));
        if (!order.getProductList().isEmpty()) for (Product product : order.getProductList()) {
            orderDao.addProductByOrder(order.getId(), product.getId());
        }
        return order.getId();
    }

    @Override
    public void addProductByOrder(int orderId, int productId) throws ServiceException {
        orderDao.addProductByOrder(orderId, productId);
    }

    @Override
    public List<Order> readAllOrdersByUser(int userId) throws ServiceException {
        List<Order> orderList = orderDao.readAllOrdersByUser(userId);
        if (!orderList.isEmpty()) {
            for (Order order : orderList) readProducts(order);
        }
        return orderList;
    }

    @Override
    public List<Integer> readAllProductsIdByOrder(int orderId) throws ServiceException {
        return orderDao.readAllProductsIdByOrder(orderId);
    }

    private void readProducts(Order order) throws ServiceException {
        List<Integer> productIdList = orderDao.readAllProductsIdByOrder(order.getId());
        if (!productIdList.isEmpty()) {
            for (Integer integer : productIdList) {
                Product product = productDao.read(integer);
                int categoryId = productDao.getCategoryId(product.getId());
                product.setCategory(categoryDao.read(categoryId));
                order.addProduct(product);
            }
        }
    }
}
