package com.commandoby.sonyShop;

import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Order;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.CategoryService;
import com.commandoby.sonyShop.service.OrderService;
import com.commandoby.sonyShop.service.ProductService;
import com.commandoby.sonyShop.service.UserService;
import com.commandoby.sonyShop.service.impl.CategoryServiceImpl;
import com.commandoby.sonyShop.service.impl.OrderServiceImpl;
import com.commandoby.sonyShop.service.impl.ProductServiceImpl;
import com.commandoby.sonyShop.service.impl.UserServiceImpl;
import com.commandoby.sonyShop.utills.DataSourceHolder;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class ApplicationServiceTest {

    public static void main(String[] args) throws ServiceException {
        EntityManager entityManager = DataSourceHolder.getInstance().getEntityManager();

        UserService userService = new UserServiceImpl(entityManager);
        CategoryService categoryService = new CategoryServiceImpl(entityManager);
        ProductService productService = new ProductServiceImpl(entityManager);
        OrderService orderService = new OrderServiceImpl(entityManager);

        //test userService
        User user = new User("Ivan", "Ivanov", "ivan",
                "ivanov", "1989-08-14", 100000);
        userService.create(user);

        User user1 = userService.getUserByEmail(user.getEmail());
        System.out.println(user1);

        user1.setBalance(90000);
        userService.update(user1);

        User user2 = userService.read(user.getId());
        System.out.println(user2);

        userService.findUsersByEmailLike("va").forEach(System.out::println);

        List<String> emailList = userService.getAllUsersEmails();
        System.out.println(emailList);

        userService.delete(user);
        System.out.println();

        //test categoryService
        Category category = new Category("Phone1", "phone1", "phone.jpeg", 1);
        categoryService.create(category);

        List<Category> categoryList = categoryService.getAllCategories();
        System.out.println(categoryList);

        categoryList.get(0).setRating(2);
        categoryService.update(categoryList.get(0));

        Category category1 = categoryService.read(category.getId());
        System.out.println(category1);

        Category category2 = categoryService.getCategoryByTag(category.getTag());
        System.out.println(category2);

        categoryService.delete(category);
        System.out.println();

        //test productService
        Category categoryProduct = new Category("Phone2", "phone2", "phone.jpeg", 1);
        Product product = new Product("Sony 10 II", "10_II.jpeg",
                "Android, экран 6\" OLED (1080x2520)", categoryProduct, 899, 10);
        productService.create(product);

        Product product1 = productService.getProductByName(product.getName());
        System.out.println(product1);

        product1.setPrice(1399);
        product1.setQuantity(0);
        productService.update(product1);

        productService.getAllProducts().forEach(System.out::println);

        productService.getAllProductsByCategory(categoryProduct).forEach(System.out::println);

        productService.getProductsByNotNullQuantity().forEach(System.out::println);

        productService.getProductsByNameLike("ny").forEach(System.out::println);

        productService.delete(product);
        categoryService.delete(categoryProduct);
        System.out.println();

        //test orderService
        Category categoryOrder = new Category("Phone4", "phone4", "phone.jpeg", 1);
        categoryService.create(categoryOrder);
        Product productOrder1 = new Product("Sony 10 II", "10_II.jpeg",
                "Android, экран 6\" OLED (1080x2520)", categoryOrder, 899, 10);
        Product productOrder2 = new Product("Sony 12 I", "12_I.jpeg",
                "Android, экран 7\" OLED (1500x3020)", categoryOrder, 1599, 12);
        productService.create(productOrder1);
        productService.create(productOrder2);
        Order order = new Order(LocalDate.now().toString());
        order.getProductList().add(productOrder1);
        order.getProductList().add(productOrder2);
        User userOrder = new User("Ivan", "Ivanov", "ivan",
                "ivanov", "1989-08-14", 100000);
        userService.create(userOrder);
        order.setUser(userOrder);
        orderService.create(order);

        Order order1 = orderService.read(order.getId());
        System.out.println(order1);

        order1.getProductList().forEach(System.out::println);

        Product productOrder3 = new Product("Sony 20 III", "20_III.jpeg",
                "Android, экран 8\" OLED (1080x2520)", categoryOrder, 2899, 250);
        productService.create(productOrder3);
        order.getProductList().add(productOrder3);
        orderService.update(order);

        orderService.readAllOrdersByUser(userOrder).forEach(System.out::println);

        User userOrder1 = userService.read(userOrder.getId());
        System.out.println(userOrder1);
        userOrder1.getOrders().forEach(System.out::println);

        userService.delete(userOrder1);
        productService.delete(productOrder1);
        productService.delete(productOrder2);
        productService.delete(productOrder3);
        categoryService.delete(categoryOrder);
    }
}
