package com.commandoby.sonyShop.classies;

import java.util.Objects;

public class User {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String data;

    public User(String name, String surname, String email, String password, String data) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.data = data;
    }

    public User(Builder builder) {
        name = builder.name;
        surname = builder.surname;
        email = builder.email;
        password = builder.password;
        data = builder.data;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(data, user.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email, password, data);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", data='" + data + '\'' +
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

        public User build() {
            return new User(this);
        }
    }
}
