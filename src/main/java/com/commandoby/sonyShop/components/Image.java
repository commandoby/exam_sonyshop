package com.commandoby.sonyShop.components;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;

@Entity
@Table(name = "images")
@Component
public class Image extends BaseEntity {
	private String imageURL;

	@Column(name = "imageURL")
	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	@Override
	public int hashCode() {
		return Objects.hash(imageURL);
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
		return Objects.equals(imageURL, other.imageURL);
	}

	@Override
	public String toString() {
		return "Image [imageURL=" + imageURL + "]";
	}
}
