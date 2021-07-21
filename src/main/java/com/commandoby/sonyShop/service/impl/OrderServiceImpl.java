package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.repository.OrderRepository;
import com.commandoby.sonyShop.repository.domain.Order;
import com.commandoby.sonyShop.repository.domain.User;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
}
