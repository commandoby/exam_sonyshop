package com.commandoby.sonyShop.controllers.search;

import com.commandoby.sonyShop.dao.domain.Product;
import com.commandoby.sonyShop.dao.domain.ShopContent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdvancedSearch {
    public static List<Product> search(String text, Integer minPrice, Integer maxPrice, String searchCategory) {
        List<Product> productList = new ArrayList<>();
        List<Product> productListDescription = new ArrayList<>();

        for (Product product : ShopContent.getProductList()) {
            if (searchCategory == null || product.getCategory().getTag().equals(searchCategory)) {
                if (product.getName().toLowerCase().contains(text.trim().toLowerCase())) {
                    if (minPrice == null || product.getPrice() >= minPrice)
                        if (maxPrice == null || product.getPrice() <= maxPrice)
                            productList.add(product);
                } else {
                    if (product.getDescription().toLowerCase().contains(text.trim().toLowerCase()))
                        if (minPrice == null || product.getPrice() >= minPrice)
                            if (maxPrice == null || product.getPrice() <= maxPrice)
                                productListDescription.add(product);
                }
            }
        }

        if (!productList.isEmpty()) productList.sort(Comparator.comparing(Product::getPrice));
        if (!productListDescription.isEmpty())
            productListDescription.sort(Comparator.comparing(Product::getPrice));

        productList.addAll(productListDescription);
        return productList;
    }
}
