package com.commandoby.sonyShop.components;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories")
@Component
public class Category extends BaseEntity{
    private String name;
    private String tag;
    private String imageName;
    private Image image;
    private int rating;
    private List<Product> productList;

    public Category() {}

    public Category(String name, String tag, String imageName, Image image, int rating) {
        this.name = name;
        this.tag = tag;
        this.imageName = imageName;
        this.image = image;
        this.rating = rating;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "tag")
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Column(name = "image_name")
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "image_id", nullable = false)
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Column(name = "rating")
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
