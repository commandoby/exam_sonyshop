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
                    if (getPriceCap(product, minPrice, maxPrice))
                        productList.add(product);
                } else {
                    if (product.getDescription().toLowerCase().contains(text.trim().toLowerCase()))
                        if (getPriceCap(product, minPrice, maxPrice))
                            productListDescription.add(product);
                }
            }
        }

        sort(productList, productListDescription);

        productList.addAll(productListDescription);
        return productList;
    }

    private static boolean getPriceCap(Product product, Integer minPrice, Integer maxPrice) {
        return (minPrice == null || product.getPrice() >= minPrice)
                && (maxPrice == null || product.getPrice() <= maxPrice);
    }

    private static void sort(List<Product> firstList, List<Product> secondList) {
        if (!firstList.isEmpty()) firstList.sort(Comparator.comparing(Product::getPrice));
        if (!secondList.isEmpty()) secondList.sort(Comparator.comparing(Product::getPrice));
    }
}
