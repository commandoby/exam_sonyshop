package com.commandoby.sonyShop.repository;

import com.commandoby.sonyShop.exceptions.RepositoryException;
import com.commandoby.sonyShop.components.Order;
import com.commandoby.sonyShop.components.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByUser(User user) throws RepositoryException;
}
