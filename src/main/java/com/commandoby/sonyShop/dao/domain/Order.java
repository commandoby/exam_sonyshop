package com.commandoby.sonyShop.dao.domain;

import com.commandoby.sonyShop.exceptions.NoFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order extends BaseEntity {
    private int orderPrice = 0;
    private String date;
    private List<Product> productList = new ArrayList<>();

    public Order() {}

    public Order(String date) {
        this.date = date;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        return orderPrice == order.orderPrice && Objects.equals(date, order.date) && Objects.equals(productList, order.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderPrice, date, productList);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderPrice=" + orderPrice +
                ", date=" + date +
                ", products=" + productList.size() +
                '}';
    }
}
