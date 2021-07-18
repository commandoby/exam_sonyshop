package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.dao.CategoryDao;
import com.commandoby.sonyShop.dao.OrderDao;
import com.commandoby.sonyShop.dao.ProductDao;
import com.commandoby.sonyShop.dao.UserDao;
import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.dao.impl.CategoryDaoImpl;
import com.commandoby.sonyShop.dao.impl.OrderDaoImpl;
import com.commandoby.sonyShop.dao.impl.ProductDaoImpl;
import com.commandoby.sonyShop.dao.impl.UserDaoImpl;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    private ProductDao productDao = new ProductDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public int create(User user) throws ServiceException {
        user.setId(userDao.create(user));
        addOrders(user);
        return user.getId();
    }

    @Override
    public User read(int id) throws ServiceException {
        User user = userDao.read(id);
        readOrders(user);
        return user;
    }

    @Override
    public void update(User user) throws ServiceException {
        userDao.update(user);
    }

    @Override
    public void delete(int id) throws ServiceException {
        userDao.delete(id);
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        return userDao.getUserByEmail(email);
    }

    private void addOrders(User user) throws ServiceException{
        if (!user.getOrders().isEmpty()) {
            for (Order order : user.getOrders()) {
                order.setId(orderDao.createOrderByUser(order, user.getId()));
                if (!order.getProductList().isEmpty()) for (Product product : order.getProductList()) {
                    orderDao.addProductByOrder(order.getId(), product.getId());
                }
            }
        }
    }

    private void readOrders(User user) throws ServiceException{
        List<Order> orderList = orderDao.readAllOrdersByUser(user.getId());
        if (!orderList.isEmpty()) {
            for (Order order : orderList) {
                List<Integer> productIdList = orderDao.readAllProductsIdByOrder(order.getId());
                if (!productIdList.isEmpty()) for (Integer integer : productIdList) {
                    Product product = productDao.read(integer);
                    int categoryId = productDao.getCategoryId(product.getId());
                    product.setCategory(categoryDao.read(categoryId));
                    order.addProduct(product);
                }
            }
            user.setOrders(orderList);
        }
    }

}
