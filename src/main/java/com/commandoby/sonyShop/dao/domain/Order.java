package com.commandoby.sonyShop.dao.domain;

import com.commandoby.sonyShop.exceptions.NoFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Component
public class Order extends BaseEntity {
    private int orderPrice = 0;
    private String date;
    private User user;
    private List<Product> productList = new ArrayList<>();

    public Order() {}

    public Order(String date) {
        this.date = date;
    }

    @Column(name = "price")
    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "orders_products", joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
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
