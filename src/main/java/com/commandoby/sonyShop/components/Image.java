package com.commandoby.sonyShop.components;

import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;

@Entity
@Table(name = "images")
@Component
public class Image extends BaseEntity {
	private byte[] image;
	private String base64Image;

	@Column(name = "image")
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Transient
	public String getBase64Image() {
		convertByteToBase64Image();
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	private void convertByteToBase64Image() {
		if (this.base64Image == null || this.base64Image.equals("")) {
			this.base64Image = Base64.getEncoder().encodeToString(this.image);
			System.out.println("here");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + Objects.hash(base64Image);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		return Objects.equals(base64Image, other.base64Image) && Arrays.equals(image, other.image);
	}
}
