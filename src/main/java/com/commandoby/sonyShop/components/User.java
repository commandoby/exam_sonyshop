package com.commandoby.sonyShop.components;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
@Component
public class User extends BaseEntity implements UserDetails {

	private String name;

	private String surname;

	@NotEmpty(message = "Email/login must not be empty")
	@Pattern(regexp = "\\S+", message = "Spaces are not allowed")
	private String email;

	@Size(min = 4, max = 50, message = "Password must be between 4 and 50 characters")
	@Pattern(regexp = "\\S+", message = "Spaces are not allowed")
	private String password;

	private LocalDate dateOfBirth;

	private int balance;

	private Image image;

	private Set<Role> roles = new HashSet<>();

	private List<Order> orders = new ArrayList<>();

	public User() {
	}

	public User(String name, String surname, String email, String password, LocalDate dateOfBirth, int balance,
			Image image, Role role) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.balance = balance;
		this.image = image;
	}

	public User(Builder builder) {
		name = builder.name;
		surname = builder.surname;
		email = builder.email;
		password = builder.password;
		dateOfBirth = builder.dateOfBirth;
		balance = builder.balance;
		image = builder.image;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "surname")
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "date_of_birth")
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "balance")
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@ManyToOne(optional = false, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "image_id", nullable = false)
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Order> getOrders() {
		return orders;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void addOrder(Order order) {
		orders.add(order);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles();
	}

	@Override
	public String getUsername() {
		return getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, dateOfBirth, email, image, name, orders, password, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return balance == other.balance && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(email, other.email) && Objects.equals(image, other.image)
				&& Objects.equals(name, other.name) && Objects.equals(orders, other.orders)
				&& Objects.equals(password, other.password) && Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname + ", email=" + email + ", password=" + password
				+ ", dateOfBirth=" + dateOfBirth + ", balance=" + balance + ", image=" + image + ", orders=" + orders
				+ "]";
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static final class Builder {
		private String name;
		private String surname;
		private String email;
		private String password;
		private LocalDate dateOfBirth;
		private int balance;
		private Image image;

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

		public Builder withDateOfBirth(LocalDate data) {
			this.dateOfBirth = data;
			return this;
		}

		public Builder withBalance(int balance) {
			this.balance = balance;
			return this;
		}

		public Builder withImage(Image image) {
			this.image = image;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}
}
