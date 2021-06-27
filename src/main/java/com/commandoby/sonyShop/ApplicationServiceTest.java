package com.commandoby.sonyShop;

import com.commandoby.sonyShop.dao.domain.Category;
import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.dao.domain.User;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.CategoryService;
import com.commandoby.sonyShop.service.ProductService;
import com.commandoby.sonyShop.service.UserService;
import com.commandoby.sonyShop.service.impl.CategoryServiceImpl;
import com.commandoby.sonyShop.service.impl.ProductServiceImpl;
import com.commandoby.sonyShop.service.impl.UserServiceImpl;

import java.util.List;

public class ApplicationServiceTest {
    public static void main(String[] args) throws ServiceException {

        //test userService
        UserService userService = new UserServiceImpl();
        User user = new User("Ivan", "Ivanov", "ivan",
                "ivanov", "1989-08-14", 100000);
        user.setId(userService.create(user));

        User user1 = userService.getUserByEmail(user.getEmail());
        System.out.println(user1);

        user1.setBalance(90000);
        userService.update(user1);

        User user2 = userService.read(user1.getId());
        System.out.println(user2);

        userService.delete(user.getId());
        System.out.println();

        //test categoryService
        CategoryService categoryService = new CategoryServiceImpl();
        Category category = new Category("Phone", "phone", "phone.jpeg", 1);
        category.setId(categoryService.create(category));

        List<Category> categoryList = categoryService.getAllCategories();
        System.out.println(categoryList);

        categoryList.get(0).setRating(2);
        categoryService.update(categoryList.get(0));

        Category category1 = categoryService.read(categoryList.get(0).getId());
        System.out.println(category1);

        categoryService.delete(category.getId());
        System.out.println();

        //test productService
        ProductService productService = new ProductServiceImpl();
        Category categoryProduct = new Category("Phone", "phone", "phone.jpeg", 1);
        categoryProduct.setId(categoryService.create(categoryProduct));
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
    }
}
