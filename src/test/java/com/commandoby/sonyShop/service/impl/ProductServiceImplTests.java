package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.components.Category;
import com.commandoby.sonyShop.components.Product;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.repository.ProductRepository;
import com.commandoby.sonyShop.repository.SearchProductsRepository;
import com.commandoby.sonyShop.service.ProductService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static com.commandoby.sonyShop.enums.RequestParamEnum.PRODUCT_LIST;

public class ProductServiceImplTests {
    private static ProductRepository productRepositoryMock;
    private static SearchProductsRepository searchProductsRepositoryMock;

    private static ProductService productService;

    private static Product product1;
    private static Product product2;
    private static ArrayList<Product> productList;

    @BeforeAll
    public static void setUp() {
        productRepositoryMock = Mockito.mock(ProductRepository.class);
        searchProductsRepositoryMock = Mockito.mock(SearchProductsRepository.class);

        productService = new ProductServiceImpl(productRepositoryMock, searchProductsRepositoryMock);

        Category category = new Category("Phone", "phone", "phone.jpeg", 1);

        product1 = Product.newBuilder().withName("Sony Xperia 10 II")
                .withCategory(category).withPrice(1000).withQuantity(5).build();
        product1.setId(1);
        product2 = Product.newBuilder().withName("Sony Xperia 1 II")
                .withCategory(category).withPrice(2500).withQuantity(1).build();
        product2.setId(2);

        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product1);
        productList.add(product1);
        productList.add(product2);
        productList.add(product2);

        productList.add(product1);
        productList.add(product1);
        productList.add(product2);
        productList.add(product1);
        productList.add(product2);

        productList.add(product2);
        productList.add(product2);
        productList.add(product1);
    }

    @Test
    public void prePagination_Test1() throws ServiceException {
        ArrayList<Product> expectedProductList = new ArrayList<>();
        expectedProductList.add(product1);
        expectedProductList.add(product1);
        expectedProductList.add(product1);
        ModelMap modelMap = new ModelMap();
        productService.prePagination(modelMap, productList, 3, 1);
        assertEquals(expectedProductList, modelMap.get(PRODUCT_LIST.getValue()));
    }

    @Test
    public void prePagination_Test2() throws ServiceException {
        ArrayList<Product> expectedProductList = new ArrayList<>();
        expectedProductList.add(product1);
        expectedProductList.add(product2);
        expectedProductList.add(product2);
        expectedProductList.add(product2);
        ModelMap modelMap = new ModelMap();
        productService.prePagination(modelMap, productList, 4, 3);
        assertEquals(expectedProductList, modelMap.get(PRODUCT_LIST.getValue()));
    }

    @Test
    public void prePagination_Test3() throws ServiceException {
        ArrayList<Product> expectedProductList = new ArrayList<>();
        expectedProductList.add(product2);
        expectedProductList.add(product2);
        expectedProductList.add(product1);
        ModelMap modelMap = new ModelMap();
        productService.prePagination(modelMap, productList, 5, 3);
        assertEquals(expectedProductList, modelMap.get(PRODUCT_LIST.getValue()));
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("All ProductServiceImpl tests are finished!");
    }
}
