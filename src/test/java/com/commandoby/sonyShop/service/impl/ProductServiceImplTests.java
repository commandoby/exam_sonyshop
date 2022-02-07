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
import java.util.HashMap;
import java.util.Map;

import static com.commandoby.sonyShop.enums.RequestParamEnum.*;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void prePagination_Test4() throws ServiceException {
        ModelMap modelMap = new ModelMap();
        productService.prePagination(modelMap, productList, 0, 1);
        assertEquals(productList, modelMap.get(PRODUCT_LIST.getValue()));
    }

    @Test
    public void prePagination_OutPageNumberTest1() throws ServiceException {
        ArrayList<Product> expectedProductList = new ArrayList<>();
        expectedProductList.add(product2);
        expectedProductList.add(product2);
        expectedProductList.add(product1);
        ModelMap modelMap = new ModelMap();
        productService.prePagination(modelMap, productList, 5, 4);
        assertEquals(expectedProductList, modelMap.get(PRODUCT_LIST.getValue()));
    }

    @Test
    public void prePagination_OutPageNumberTest2() throws ServiceException {
        ModelMap modelMap = new ModelMap();
        productService.prePagination(modelMap, productList, 5, 5);
        assertEquals(3, modelMap.get(PAGE_NUMBER.getValue()));
    }

    @Test
    public void prePagination_MaxPagesTest() throws ServiceException {
        ModelMap modelMap = new ModelMap();
        productService.prePagination(modelMap, productList, 3, 1);
        assertEquals(5, modelMap.get(PAGE_MAX.getValue()));
    }

    @Test
    public void defaultParamsStringMap_Test() throws ServiceException {
        Map<String, String> map = new HashMap<>();
        productService.defaultParamsStringMap(map);
        assertEquals("Price+", map.get(SEARCH_COMPARING.getValue()));
    }

    @Test
    public void defaultParamsIntegerMap_Test() throws ServiceException {
        Map<String, Integer> map = new HashMap<>();
        map.put(MIN_PRICE.getValue(), 1000);
        map.put(MAX_PRICE.getValue(), 500);
        productService.defaultParamsIntegerMap(map);
        assertEquals(1000, map.get(MAX_PRICE.getValue()));
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("All ProductServiceImpl tests are finished!");
    }
}
