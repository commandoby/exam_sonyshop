package com.commandoby.sonyShop.service.impl;

import com.commandoby.sonyShop.repository.ProductRepository;
import com.commandoby.sonyShop.repository.SearchProductsRepository;
import com.commandoby.sonyShop.components.Category;
import com.commandoby.sonyShop.components.Product;
import com.commandoby.sonyShop.exceptions.ServiceException;
import com.commandoby.sonyShop.service.ProductService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.commandoby.sonyShop.enums.RequestParamEnum.*;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final SearchProductsRepository searchProductsRepository;

	public ProductServiceImpl(ProductRepository productRepository, SearchProductsRepository searchProductsRepository) {
		this.productRepository = productRepository;
		this.searchProductsRepository = searchProductsRepository;
	}

	@Override
	public int create(Product product) throws ServiceException {
		productRepository.save(product);
		return product.getId();
	}

	@Override
	public Product read(int id) throws ServiceException {
		Product product = productRepository.findById(id).orElseThrow(() -> new ServiceException(
				"Error retrieving a product from the database by ID: " + id + ".", new Exception()));
		product.setViews(product.getViews() + 1);
		update(product);
		return product;
		
	}

	@Override
	public void update(Product product) throws ServiceException {
		productRepository.save(product);
	}

	@Override
	public void delete(Product product) throws ServiceException {
		productRepository.delete(product);
	}

	@Override
	public List<Product> getAllProducts() throws ServiceException {
		List<Product> products = new ArrayList<>();
		Optional.ofNullable(productRepository.findAll())
				.orElseThrow(() -> new ServiceException("Error getting a list of all products.", new Exception()))
				.forEach(products::add);
		return products;
	}

	@Override
	public List<Product> getAllProductsByCategory(Category category) throws ServiceException {
		Optional<List<Product>> products = Optional.ofNullable(productRepository.getAllByCategory(category));
		return products.orElseThrow(() -> new ServiceException(
				"Error retrieving the list of products for category: " + category.getName() + ".", new Exception()));
	}

	@Override
	public Product getProductByName(String name) throws ServiceException {
		Optional<Product> product = Optional.ofNullable(productRepository.getProductByName(name));
		return product.orElseThrow(
				() -> new ServiceException("Error retrieving the product for name: " + name + ".", new Exception()));
	}

	@Override
	public Page<Product> getProductsByCategoryNotNull(Category category, Pageable pageable) throws ServiceException {
		Optional<Page<Product>> products = Optional.ofNullable(productRepository.getAllByCategory(category, pageable));
		return products.orElseThrow(() -> new ServiceException(
				"Error retrieving the list of products for category: " + category.getName() + ".", new Exception()));
	}

	@Override
	public Page<Product> getSearchProductsByParams(Map<String, String> paramsStringMap,
			Map<String, Integer> paramsIntegerMap) throws ServiceException {
		return searchProductsRepository.searchProductsByParams(paramsStringMap, paramsIntegerMap);
	}

	@Override
	public Map<String, String> defaultParamsStringMap(Map<String, String> paramsStringMap) throws ServiceException {
		paramsStringMap.putIfAbsent(SEARCH_VALUE.getValue(), "");
		paramsStringMap.putIfAbsent(CATEGORY_TAG.getValue(), "");
		paramsStringMap.putIfAbsent(IS_QUANTITY.getValue(), "");
		if (paramsStringMap.get(SEARCH_COMPARING.getValue()) == null
				|| paramsStringMap.get(SEARCH_COMPARING.getValue()).equals("")) {
			paramsStringMap.put(SEARCH_COMPARING.getValue(), "Price+");
		}
		return paramsStringMap;
	}

	@Override
	public Map<String, Integer> defaultParamsIntMap(Map<String, Integer> paramsIntMap) throws ServiceException {
		paramsIntMap.putIfAbsent(PAGE_ITEMS.getValue(), 5);
		paramsIntMap.putIfAbsent(PAGE_NUMBER.getValue(), 1);
		if (paramsIntMap.get(MIN_PRICE.getValue()) != null && paramsIntMap.get(MIN_PRICE.getValue()) < 0) {
			paramsIntMap.put(MIN_PRICE.getValue(), 0);
		}
		if (paramsIntMap.get(MIN_PRICE.getValue()) != null && paramsIntMap.get(MAX_PRICE.getValue()) != null
				&& paramsIntMap.get(MAX_PRICE.getValue()) < paramsIntMap.get(MIN_PRICE.getValue())) {
			paramsIntMap.put(MAX_PRICE.getValue(), paramsIntMap.get(MIN_PRICE.getValue()));
		}
		return paramsIntMap;
	}
}
