package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.repository.UserRepository;
import com.commandoby.sonyShop.repository.domain.User;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int create(User user) throws ServiceException {
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public User read(int id) throws ServiceException {
        return userRepository.findById(id).orElseThrow(() ->
                new ServiceException("Error retrieving a user from the database by ID: " + id + ".", new Exception())
        );
    }

    @Override
    public void update(User user) throws ServiceException {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) throws ServiceException {
        userRepository.delete(user);
    }

    @Override
    public User getUserByEmail(String email) throws ServiceException {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public List<User> findUsersByEmailLike(String email) throws ServiceException {
        return userRepository.findAllByEmailIsLike(email);
    }
}
