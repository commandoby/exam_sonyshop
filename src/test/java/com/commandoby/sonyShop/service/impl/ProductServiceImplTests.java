package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.repository.ProductRepository;
import com.commandoby.sonyShop.repository.SearchProductsRepository;
import com.commandoby.sonyShop.service.ProductService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static com.commandoby.sonyShop.enums.RequestParamEnum.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceImplTests {
	private static ProductRepository productRepositoryMock;
	private static SearchProductsRepository searchProductsRepositoryMock;

    private static ProductService productService;

    @BeforeAll
    public static void setUp() {
    	productRepositoryMock = Mockito.mock(ProductRepository.class);
    	searchProductsRepositoryMock = Mockito.mock(SearchProductsRepository.class);
    	
    	productService = new ProductServiceImpl(productRepositoryMock, searchProductsRepositoryMock);
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
        productService.defaultParamsIntMap(map);
        assertEquals(1000, map.get(MAX_PRICE.getValue()));
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("All ProductServiceImpl tests are finished!");
    }
}
