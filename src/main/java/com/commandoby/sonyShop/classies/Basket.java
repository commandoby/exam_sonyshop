package com.commandoby.sonyShop.classies;

import com.commandoby.sonyShop.exceptions.NoFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Basket {
    private List<Product> productList;
    private int basketPrice = 0;

    public Basket() {
        productList = new ArrayList<>();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public int getBasketPrice() {
        return basketPrice;
    }

    public void addProduct(Product product) {
        basketPrice += product.getPrice();
        productList.add(product);
    }

    public void removeProduct(int id) throws NoFoundException {
        if (productList.get(id) != null) {
            basketPrice -= productList.get(id).getPrice();
            productList.remove(id);
        } else {
            throw new NoFoundException("Will not find a product to remove by id: " + id);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return basketPrice == basket.basketPrice && Objects.equals(productList, basket.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productList, basketPrice);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "productList=" + productList +
                ", basketPrice=" + basketPrice +
                '}';
    }
}
