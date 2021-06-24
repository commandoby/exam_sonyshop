package com.commandoby.sonyShop.classies;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String data;
    private int balance;
    private List<Order> orders = new ArrayList<>();

    public User(String name, String surname, String email, String password, String data, int balance) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.data = data;
        this.balance = balance;
    }

    public User(Builder builder) {
        name = builder.name;
        surname = builder.surname;
        email = builder.email;
        password = builder.password;
        data = builder.data;
        balance = builder.balance;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getData() {
        return data;
    }

    public int getBalance() {
        return balance;
    }

    public void updateBalance(int distinction) {
        balance += distinction;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return balance == user.balance && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(data, user.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email, password, data, balance);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", data='" + data + '\'' +
                ", balance=" + balance +
                '}';
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String surname;
        private String email;
        private String password;
        private String data;
        private int balance;

        private Builder() {
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

        public Builder withData(String data) {
            this.data = data;
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
