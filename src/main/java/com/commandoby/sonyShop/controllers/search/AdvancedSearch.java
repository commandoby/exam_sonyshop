package com.commandoby.sonyShop.controllers.search;

import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AdvancedSearch {

    private final Logger log = Logger.getLogger(getClass());
    private final Map<String, Comparator> SORT_MAP = new HashMap<>();
    private final ProductService productService;

    public AdvancedSearch(ProductService productService) {
        this.productService = productService;
    }

    {
        SORT_MAP.put("Price+", Comparator.comparingInt(Product::getPrice));
        SORT_MAP.put("Price-", Comparator.comparingInt(Product::getPrice).reversed());
        SORT_MAP.put("Name+", Comparator.comparing(Product::getName));
        SORT_MAP.put("Name-", Comparator.comparing(Product::getName).reversed());
    }

    public List<Product> search(String text, Integer minPrice, Integer maxPrice,
                                String searchCategory, String searchComparing) {
        List<Product> productsName = new ArrayList<>();
        List<Product> productsDescription = new ArrayList<>();
        Set<Product> sortProducts = new LinkedHashSet<>();

        productsName = getProductsByName(text);
        productsDescription = getProductsByDescription(text);

        sort(productsName, searchComparing);
        sort(productsDescription, searchComparing);

        getSortProducts(sortProducts, productsName, searchCategory, minPrice, maxPrice);
        getSortProducts(sortProducts, productsDescription, searchCategory, minPrice, maxPrice);

        return new ArrayList<>(sortProducts);
    }

    private List<Product> getProductsByName(String text) {
        String searchText = text.trim();
        List<Product> products = null;
        try {
            products = productService.getProductsByNameLike(searchText);
        } catch (ServiceException e) {
            log.warn(e);
        }
        return products;
    }

    private List<Product> getProductsByDescription(String text) {
        String searchText = text.trim();
        List<Product> products = null;
        try {
            products = productService.getProductsByDescriptionLike(searchText);
        } catch (ServiceException e) {
            log.warn(e);
        }
        return products;
    }

    private void getSortProducts(Set<Product> sortProducts, List<Product> products, String searchCategory,
                                 Integer minPrice, Integer maxPrice) {
        for (Product product : products)
            if (searchCategory == null || searchCategory.equals("")
                    || product.getCategory().getTag().equals(searchCategory))
                if (getPriceCap(product, minPrice, maxPrice))
                    sortProducts.add(product);
    }

    private boolean getPriceCap(Product product, Integer minPrice, Integer maxPrice) {
        return (minPrice == null || product.getPrice() >= minPrice)
                && (maxPrice == null || product.getPrice() <= maxPrice);
    }

    private void sort(List<Product> products, String searchComparing) {
        if (!products.isEmpty()) products.sort(SORT_MAP.get(searchComparing));
    }
}
