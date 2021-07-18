package com.commandoby.sonyShop.dao.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User extends BaseEntity {
    private String name;
    private String surname;
    private final String email;
    private String password;
    private String dateOfBirth;
    private int balance;
    private List<Order> orders = new ArrayList<>();

    public User(String name, String surname, String email, String password, String dateOfBirth, int balance) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.balance = balance;
    }

    public User(Builder builder) {
        id = builder.id;
        name = builder.name;
        surname = builder.surname;
        email = builder.email;
        password = builder.password;
        dateOfBirth = builder.dateOfBirth;
        balance = builder.balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return balance == user.balance && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(dateOfBirth, user.dateOfBirth) && Objects.equals(orders, user.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email, password, dateOfBirth, balance, orders);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", balance=" + balance +
                ", orders=" + orders.size() +
                '}';
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private int id;
        private String name;
        private String surname;
        private String email;
        private String password;
        private String dateOfBirth;
        private int balance;

        private Builder() {}

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withDateOfBirth(String data) {
            this.dateOfBirth = data;
            return this;
        }

        public Builder withBalance(int balance) {
            this.balance = balance;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
