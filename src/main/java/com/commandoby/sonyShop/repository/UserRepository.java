package com.commandoby.sonyShop.repository;

import com.commandoby.sonyShop.exceptions.RepositoryException;
import com.commandoby.sonyShop.components.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByEmail(String email) throws RepositoryException;

    List<User> findAllByEmailIsLike(String text) throws RepositoryException;
}
