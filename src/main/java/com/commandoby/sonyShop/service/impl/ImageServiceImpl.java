package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.components.Image;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.repository.ImageRepository;
import com.commandoby.sonyShop.service.BaseService;

public class ImageServiceImpl implements BaseService<Image> {

	private final ImageRepository imageRepository;

	public ImageServiceImpl(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}

	@Override
	public int create(Image image) throws ServiceException {
		imageRepository.save(image);
		return image.getId();
	}

	@Override
	public Image read(int id) throws ServiceException {
		return imageRepository.findById(id)
				.orElseThrow(() -> new ServiceException("Error retrieving a image from the database by ID: " + id + ".",
						new Exception()));
	}

	@Override
	public void update(Image image) throws ServiceException {
		imageRepository.save(image);
	}

	@Override
	public void delete(Image image) throws ServiceException {
		imageRepository.delete(image);
	}

}
