package com.commandoby.sonyShop.components;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
@Component
public class Product extends BaseEntity {
	private String name;
	private String description;
	private Category category;
	private Image image;
	private int price;
	private Integer year;
	private int quantity;
	private int views;
	private List<Order> orders;

	public Product() {
	}

	public Product(String name, String description, Category category, Image image, int price, Integer year,
			int quantity, int views) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.image = image;
		this.price = price;
		this.year = checkYear(year);
		this.quantity = quantity;
		this.views = views;
	}

	public Product(Builder builder) {
		name = builder.name;
		description = builder.description;
		category = builder.category;
		image = builder.image;
		price = builder.price;
		year = checkYear(builder.year);
		quantity = builder.quantity;
		views = builder.views;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(optional = false, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category_id", nullable = false)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne(optional = false, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "image_id", nullable = false)
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Column(name = "price")
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Column(name = "year")
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = checkYear(year);
	}

	@Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "views")
	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	@ManyToMany(mappedBy = "productList")
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	private Integer checkYear(Integer year) {
		if (year != null && year < 1993) {
			return 1993;
		}
		return year;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, description, image, name, orders, price, quantity, views, year);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(category, other.category) && Objects.equals(description, other.description)
				&& Objects.equals(image, other.image) && Objects.equals(name, other.name)
				&& Objects.equals(orders, other.orders) && price == other.price && quantity == other.quantity
				&& views == other.views && Objects.equals(year, other.year);
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description + ", category=" + category + ", image=" + image
				+ ", price=" + price + ", year=" + year + ", quantity=" + quantity + ", views=" + views + ", orders="
				+ orders + "]";
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static final class Builder {
		private String name;
		private String description;
		private Category category;
		private Image image;
		private int price;
		private Integer year;
		private int quantity;
		private int views;

		private Builder() {
		}

		public Builder withName(String name) {
			this.name = name;
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

		public Builder withImage(Image image) {
			this.image = image;
			return this;
		}

		public Builder withPrice(int price) {
			this.price = price;
			return this;
		}

		public Builder withYear(Integer year) {
			this.year = year;
			return this;
		}

		public Builder withQuantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder withViews(int views) {
			this.views = views;
			return this;
		}

		public Product build() {
			return new Product(this);
		}
	}
}
