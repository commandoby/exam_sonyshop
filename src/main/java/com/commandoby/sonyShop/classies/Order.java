package com.commandoby.sonyShop.classies;

import com.commandoby.sonyShop.exceptions.NoFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private int orderPrice = 0;
    private List<Product> productList;

    public Order() {
        productList = new ArrayList<>();
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addProduct(Product product) {
        orderPrice += product.getPrice();
        productList.add(product);
    }

    public void removeProduct(int id) throws NoFoundException {
        if (productList.get(id) != null) {
            orderPrice -= productList.get(id).getPrice();
            productList.remove(id);
        } else {
            throw new NoFoundException("Will not find a product to remove by id: " + id);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderPrice == order.orderPrice && Objects.equals(productList, order.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productList, orderPrice);
    }

    @Override
    public String toString() {
        return "Order{" +
                "productList=" + productList +
                ", orderPrice=" + orderPrice +
                '}';
    }
}
