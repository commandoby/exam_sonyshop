package com.commandoby.sonyShop;

import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.impl.UserServiceImpl;

public class Application {
    public static void main(String[] args) throws ServiceException {
        User user = new User("Ivan", "Ivanov", "ivan",
                "ivanov", "1989-08-14", 100000);
        UserServiceImpl usi = new UserServiceImpl();

        usi.create(user);

        User user1 = usi.getUserByEmail(user.getEmail());
        System.out.println(user1);

        user1.setBalance(90000);
        usi.update(user1);

        User user2 = usi.read(user1.getId());
        System.out.println(user2);

        usi.delete(user2.getId());
    }
}
