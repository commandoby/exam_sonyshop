package com.commandoby.sonyShop.service.search;

import com.commandoby.sonyShop.classies.Product;
import com.commandoby.sonyShop.classies.ShopContent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class AdvancedSearch {
    public static List<Product> search(String text, Integer minPrice, Integer maxPrice) {
        Pattern pattern = Pattern.compile(".*" + text.trim().toLowerCase() + ".*");
        List<Product> productList = new ArrayList<>();
        List<Product> productListDescription = new ArrayList<>();

        for (Product product : ShopContent.getProductList()) {
            if (product.getName().toLowerCase().matches(pattern.pattern())) {
                if (minPrice == null || product.getPrice() >= minPrice) {
                    if (maxPrice == null || product.getPrice() <= maxPrice) {
                        productList.add(product);
                    }
                }
            } else {
                if (product.getDescription().toLowerCase().matches(pattern.pattern())) {
                    if (minPrice == null || product.getPrice() >= minPrice) {
                        if (maxPrice == null || product.getPrice() <= maxPrice) {
                            productListDescription.add(product);
                        }
                    }
                }
            }
        }

        if (!productList.isEmpty()) productList.sort(Comparator.comparing(Product::getPrice));
        if (!productListDescription.isEmpty()) productList.sort(Comparator.comparing(Product::getPrice));

        productList.addAll(productListDescription);
        return productList;
    }
}
