package com.commandoby.sonyShop.dao.domain;

import java.util.Objects;

public class Category extends BaseEntity implements ShopObject{
    private String name;
    private String tag;
    private String imageName;
    private int rating;

    public Category(String name, String tag, String imageName, int rating) {
        this.name = name;
        this.tag = tag;
        this.imageName = imageName;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public String getImageName() {
        return imageName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return rating == category.rating && Objects.equals(name, category.name) && Objects.equals(imageName, category.imageName) && Objects.equals(tag, category.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageName, tag, rating);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                ", imageName='" + imageName + '\'' +
                ", rating=" + rating +
                '}';
    }
}
