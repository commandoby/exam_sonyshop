package com.commandoby.sonyShop.components;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.mysql.cj.jdbc.Blob;

import jakarta.persistence.Column;

@Entity
@Table(name = "images")
@Component
public class Image extends BaseEntity {
	private byte[] image;

	@Column(name = "image")
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
