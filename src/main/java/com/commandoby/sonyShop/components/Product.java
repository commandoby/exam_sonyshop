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
	private int quantity;
	private List<Order> orders;

	public Product() {
	}

	public Product(String name, String description, Category category, Image image, int price, int quantity) {
		this.name = name;
		this.description = description;
		this.category = category;
		this.image = image;
		this.price = price;
		this.quantity = quantity;
	}

	public Product(Builder builder) {
		name = builder.name;
		description = builder.description;
		category = builder.category;
		image = builder.image;
		price = builder.price;
		quantity = builder.quantity;
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

	@Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@ManyToMany(mappedBy = "productList")
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, description, image, name, orders, price, quantity);
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
				&& Objects.equals(orders, other.orders) && price == other.price && quantity == other.quantity;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description + ", category=" + category + ", image=" + image
				+ ", price=" + price + ", quantity=" + quantity + ", orders=" + orders + "]";
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
		private int quantity;

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

		public Builder withQuantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public Product build() {
			return new Product(this);
		}
	}
}
