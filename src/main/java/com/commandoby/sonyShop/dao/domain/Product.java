package com.commandoby.sonyShop.dao.domain;

import java.util.Objects;

public class Product extends BaseEntity implements ShopObject {
    private String name;
    private String imageName;
    private String description;
    private Category category;
    private int price;

    public Product(String name, String imageName, String description, Category category, int price) {
        this.name = name;
        this.imageName = imageName;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public Product(Builder builder) {
        id = builder.id;
        name = builder.name;
        imageName = builder.imageName;
        description = builder.description;
        category = builder.category;
        price = builder.price;
    }

    public String getName() {
        return name;
    }

    public String getImageName() {
        return imageName;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && Objects.equals(name, product.name) && Objects.equals(imageName, product.imageName) && Objects.equals(category, product.category) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageName, category, description, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageName='" + imageName + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category.getName() + '\''  +
                ", price=" + price +
                '}';
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private static final class Builder {
        private int id;
        private String name;
        private String imageName;
        private String description;
        private Category category;
        private int price;

        private Builder() {}

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withImageName(String imageName) {
            this.imageName = imageName;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder withPrice(int price) {
            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
