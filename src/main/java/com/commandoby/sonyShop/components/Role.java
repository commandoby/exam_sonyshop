package com.commandoby.sonyShop.components;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "roles")
@Component
public class Role extends BaseEntity implements GrantedAuthority {
	
	private String name;
	private Set<User> users;

	public Role(String name) {
		super();
		this.name = name;
	}
	
	public Role(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy = "roles")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String getAuthority() {
		return getName();
	}

}
