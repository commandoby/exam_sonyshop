package com.commandoby.sonyShop.repository;

import com.commandoby.sonyShop.exceptions.RepositoryException;
import com.commandoby.sonyShop.components.Category;
import com.commandoby.sonyShop.components.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {
	
	long count();

	long countByCategory(Category category);

    List<Product> getAllByCategory(Category category) throws RepositoryException;

    Product getProductByName(String productName) throws RepositoryException;

    Page<Product> getAllByCategory(Category category, Pageable pageable) throws RepositoryException;
}
