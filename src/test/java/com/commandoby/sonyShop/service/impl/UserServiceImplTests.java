package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.components.User;
import com.commandoby.sonyShop.repository.UserRepository;
import com.commandoby.sonyShop.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

import java.time.LocalDate;

public class UserServiceImplTests {
    private static UserRepository userRepositoryMock;

    private static UserService userService;

    private static User user;

    @BeforeAll
    public static void setUp() {
        userRepositoryMock = Mockito.mock(UserRepository.class);

        userService = new UserServiceImpl(userRepositoryMock);

        user = User.newBuilder()
                .withEmail("ivan")
                .withName("Ivan")
                .withSurname("Ivanov")
//                .withDateOfBirth(new LocalDate(1990, 10, 20))
                .withBalance(10000)
                .withPassword("ivan")
                .build();
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("All UserServiceImpl tests are finished!");
    }
}
