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

import java.time.LocalDate;
import java.util.List;

public class ApplicationServiceTest {
    public static void main(String[] args) throws ServiceException {
        UserService userService = new UserServiceImpl();
        CategoryService categoryService = new CategoryServiceImpl();
        ProductService productService = new ProductServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        //test userService
        User user = new User("Ivan", "Ivanov", "ivan",
                "ivanov", "1989-08-14", 100000);
        userService.create(user);

        User user1 = userService.getUserByEmail(user.getEmail());
        System.out.println(user1);

        user1.setBalance(90000);
        userService.update(user1);

        User user2 = userService.read(user1.getId());
        System.out.println(user2);

        userService.delete(user);
        System.out.println();

        //test categoryService
        /*Category category = new Category("Phone1", "phone1", "phone.jpeg", 1);
        categoryService.create(category);

        List<Category> categoryList = categoryService.getAllCategories();
        System.out.println(categoryList);

        categoryList.get(0).setRating(2);
        categoryService.update(categoryList.get(0));

        Category category1 = categoryService.read(categoryList.get(0).getId());
        System.out.println(category1);

        categoryService.delete(category.getId());
        System.out.println();

        //test productService
        Category categoryProduct = new Category("Phone2", "phone2", "phone.jpeg", 1);
        categoryService.create(categoryProduct);
        Product product = new Product("Sony 10 II", "10_II.jpeg",
                "Android, экран 6\" OLED (1080x2520)", categoryProduct, 899);
        productService.create(product);

        Product product1 = productService.getProductByName(product.getName());
        System.out.println(product1);

        product1.setPrice(1399);
        productService.update(product1);

        Product product2 = productService.read(product1.getId());
        System.out.println(product2);

        List<Product> productList = productService.getAllProducts();
        System.out.println(productList);

        productList = productService.getAllProductsByCategory(categoryProduct);
        System.out.println(productList);

        categoryService.delete(categoryProduct.getId());
        System.out.println();

        //test orderService
        Category categoryOrder = new Category("Phone4", "phone4", "phone.jpeg", 1);
        categoryService.create(categoryOrder);
        Product productOrder1 = new Product("Sony 10 II", "10_II.jpeg",
                "Android, экран 6\" OLED (1080x2520)", categoryOrder, 899);
        Product productOrder2 = new Product("Sony 12 I", "12_I.jpeg",
                "Android, экран 7\" OLED (1500x3020)", categoryOrder, 1599);
        productService.create(productOrder1);
        productService.create(productOrder2);
        Order order = new Order(LocalDate.now().toString());
        order.addProduct(productOrder1);
        order.addProduct(productOrder2);
        User userOrder = new User("Ivan", "Ivanov", "ivan",
                "ivanov", "1989-08-14", 100000);
        userService.create(userOrder);
        orderService.createOrderByUser(order, userOrder.getId());

        Order order1 = orderService.read(order.getId());
        System.out.println(order1);

        List<Product> productListOrder = order1.getProductList();
        System.out.println(productListOrder);

        Product productOrder3 = new Product("Sony 20 III", "20_III.jpeg",
                "Android, экран 8\" OLED (1080x2520)", categoryOrder, 2899);
        productService.create(productOrder3);
        orderService.addProductByOrder(order.getId(), productOrder3.getId());

        List<Order> orderListByUser = orderService.readAllOrdersByUser(userOrder.getId());
        System.out.println(orderListByUser);

        User userOrder1 = userService.read(userOrder.getId());
        userService.delete(userOrder.getId());

        userService.create(userOrder1);
        System.out.println(userOrder1);

        userService.delete(userOrder1.getId());
        categoryService.delete(categoryOrder.getId());*/
    }
}
