package com.commandoby.sonyShop.controllers.search;

import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.ProductService;
import com.commandoby.sonyShop.service.impl.ProductServiceImpl;
import org.apache.log4j.Logger;

import java.util.*;

public class AdvancedSearch {
    private ProductService productService = new ProductServiceImpl();
    private Logger log = Logger.getLogger(getClass());

    public List<Product> search(String text, Integer minPrice, Integer maxPrice, String searchCategory) {
        List<Product> shopProducts = getProducts();
        List<Product> resultProductsName = new ArrayList<>();
        List<Product> resultProductsDescription = new ArrayList<>();
        Set<Product> productSet = new HashSet<>();

        if (shopProducts != null)
            for (Product product : shopProducts) {
                if (searchCategory == null || product.getCategory().getTag().equals(searchCategory)) {
                    if (product.getName().toLowerCase().contains(text.trim().toLowerCase())) {
                        if (getPriceCap(product, minPrice, maxPrice))
                            resultProductsName.add(product);
                    } else {
                        if (product.getDescription().toLowerCase().contains(text.trim().toLowerCase()))
                            if (getPriceCap(product, minPrice, maxPrice))
                                resultProductsDescription.add(product);
                    }
                }
            }

        sort(resultProductsName);
        sort(resultProductsDescription);
        productSet.addAll(resultProductsName);
        productSet.addAll(resultProductsDescription);

        return new ArrayList<>(productSet);
    }

    private List<Product> getProducts() {
        List<Product> products = null;
        try {
            products = productService.getAllProducts();
        } catch (ServiceException e) {
            log.warn(e);
        }
        return products;
    }

    private boolean getPriceCap(Product product, Integer minPrice, Integer maxPrice) {
        return (minPrice == null || product.getPrice() >= minPrice)
                && (maxPrice == null || product.getPrice() <= maxPrice);
    }

    private void sort(List<Product> products) {
        if (!products.isEmpty()) products.sort(Comparator.comparing(Product::getPrice));
    }
}
