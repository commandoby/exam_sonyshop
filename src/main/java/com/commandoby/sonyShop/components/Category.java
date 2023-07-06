package com.commandoby.sonyShop.components;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categories")
@Component
public class Category extends BaseEntity {
	private String name;
	private String tag;
	private Image image;
	private int rating;
	private List<Product> productList;

	public Category() {
	}

	public Category(String name, String tag, Image image, int rating) {
		this.name = name;
		this.tag = tag;
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
	public int hashCode() {
		return Objects.hash(image, name, productList, rating, tag);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(image, other.image) && Objects.equals(name, other.name)
				&& Objects.equals(productList, other.productList) && rating == other.rating
				&& Objects.equals(tag, other.tag);
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", tag=" + tag + ", image=" + image + ", rating=" + rating + ", productList="
				+ productList + "]";
	}
}
