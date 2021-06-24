package com.commandoby.sonyShop.classies;

import java.util.Objects;

public class Category implements ShopObject{
    private String name;
    private String imageName;
    private String tag;

    public Category(String name, String imageName, String tag) {
        this.name = name;
        this.imageName = imageName;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public String getImageName() {
        return imageName;
    }

    public String getTag() {
        return tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(imageName, category.imageName) && Objects.equals(tag, category.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageName, tag);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", imageName='" + imageName + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
